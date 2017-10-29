package ru.gdgkazan.pictureofdaymvvm.screen.pictures;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.pictureofdaymvvm.BR;
import ru.gdgkazan.pictureofdaymvvm.R;
import ru.gdgkazan.pictureofdaymvvm.content.DayPicture;
import ru.gdgkazan.pictureofdaymvvm.repository.RepositoryProvider;
import ru.gdgkazan.pictureofdaymvvm.screen.day.DayPictureActivity;
import ru.gdgkazan.pictureofdaymvvm.utils.Dates;
import rx.Observable;

/**
 * @author Alexey Pakhtusov
 */
public class PicturesViewModel extends BaseObservable {

    private final Context mActivityContext;
    private LifecycleHandler mLifecycleHandler;
    private List<DayPicture> mPictures;

    private int mDay, mBulkLoad;
    private List<String> mDates;
    private boolean mIsLoading, mIsError;

    public PicturesViewModel(@NonNull Context activityContext,
                             @NonNull LifecycleHandler lifecycleHandler) {
        mActivityContext = activityContext;
        mLifecycleHandler = lifecycleHandler;
        mPictures = new ArrayList<>();
    }

    public void init() {
        mPictures.clear();
        mDay = 0;
        // Будем заргужать за раз по bulk_load элементов
        mBulkLoad = mActivityContext.getResources().getInteger(R.integer.bulk_load);
        mDates = Dates.getDaysOfCurrentYear();
        load();
    }

    private void load() {
        List<DayPicture> pictures = new ArrayList<>();
        Observable.from(mDates.subList(mDay, mDay + mBulkLoad <= mDates.size() ? mDay + mBulkLoad : mDates.size()))
                .flatMap(date -> RepositoryProvider.provideNasaRepository().datePicture(date))
                .doOnSubscribe(() -> { setLoading(true); setError(false); })
                .doAfterTerminate(() -> handlePicture(pictures))
                .compose(mLifecycleHandler.reload(R.id.pictures_request))
                .subscribe(pictures::add, this::handleError);
        mDay += mBulkLoad;
    }

    public void handlePicture(@NonNull List<DayPicture> pictures) {
        // Обновляем список
        mPictures.addAll(pictures);
        notifyPropertyChanged(BR.pictures);
        setLoading(false);
    }

    public void handleError(Throwable throwable) {
        mDay -= mBulkLoad;
        // Если мы не успели подгрузить первую порцию данных, то показываем ошибку
        if (mPictures.size() == 0) {
            setError(true);
        }
    }

    public void onItemClick(@NonNull View view, @NonNull DayPicture dayPicture) {
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        DayPictureActivity.navigate((AppCompatActivity) mActivityContext, imageView, dayPicture);
    }

    @VisibleForTesting
    void setLoading(boolean isLoading) {
        mIsLoading = isLoading;
        notifyPropertyChanged(BR.loading);
    }

    @VisibleForTesting
    void setError(boolean isError) {
        mIsError = isError;
        notifyPropertyChanged(BR.error);
    }

    /* Для всех @Bindable методов важно иметь правильное имя, иначе они не будут видны из xml.
     * Названия методов должны соответствовать именам полей модели, которые мы сами задаем в xml,
     * также имена могут начинатся на is, get (возможно еще какие-нибудь типичные префиксы гетеров).
     *
     * Например, если мы в xml указали model.loading, то мы должны назвать метод isLoading
     * (если метод должен возвращать тип boolean) или getLoading (также подходит для boolean);
     * Например, если мы в xml указали model.layoutManager, то должны назвать метод getLayoutManager
     * (если метод должен возвращать какой-либо тип данных).
     *
     * Чем то похоже на Dagger 2, где сгенерированный класс, соответствующий какому-либо компоненту,
     * начинается с префикса Dagger (например, DaggerИмяКомпонента), и мы уже сами через класс
     * DaggerИмяКомпонента манипулируем зависимостями.
     * В Data Binding, судя по всему, похожая схема работы: чтобы фреймворк Data Binding смог
     * получить объект, то метод для его получения должен начинаться с get (например, getИмяОбъекта) */

    @Bindable
    public boolean isLoading() {
        return mIsLoading;
    }

    @Bindable
    public boolean isError() {
        return mIsError;
    }

    @NonNull
    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        int columns = mActivityContext.getResources().getInteger(R.integer.columns_count);
        return new GridLayoutManager(mActivityContext, columns);
    }

    @NonNull
    @Bindable
    public RecyclerView.OnScrollListener getScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                /* Как только на экране стал виден последний элемент и было хоть какое-нибудь
                 * пролистывание списка вверх, сразу начинаем подгрузку следющей порции элементов */
                if (gridLayoutManager.findLastVisibleItemPosition() == mDay - 1 && dy >= 0) {
                    load();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        };
    }

    @NonNull
    @Bindable
    public PicturesAdapter getAdapter() {
        TypedValue typedValue = new TypedValue();
        mActivityContext.getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = mActivityContext.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, mActivityContext.getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((mActivityContext.getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = mActivityContext.getResources().getInteger(R.integer.columns_count);
        int imageWidth = mActivityContext.getResources().getDisplayMetrics().widthPixels / columns;

        return new PicturesAdapter(new ArrayList<>(), imageWidth, imageHeight);
    }

    @NonNull
    @Bindable
    public List<DayPicture> getPictures() {
        return mPictures;
    }
}
