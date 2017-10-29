package ru.gdgkazan.pictureofdaymvvm.screen.day;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.pictureofdaymvvm.R;
import ru.gdgkazan.pictureofdaymvvm.content.DayPicture;
import ru.gdgkazan.pictureofdaymvvm.repository.RepositoryProvider;

import ru.gdgkazan.pictureofdaymvvm.BR;
import rx.Observable;

/**
 * @author Alexey Pakhtusov
 */
public class DayViewModel extends BaseObservable {

    private DayPicture mDayPicture;

//    private final LifecycleHandler mLifecycleHandler;

    private boolean mIsLoading = false;

    private boolean mIsError = false;

//    public DayViewModel(@NonNull LifecycleHandler lifecycleHandler) {
//        mLifecycleHandler = lifecycleHandler;
//    }
    public DayViewModel(@NonNull DayPicture dayPicture) {
        mDayPicture = dayPicture;
    }

    public void init() {
        setLoading(false);
        setError(false);
//        load(mLifecycleHandler.load(R.id.day_picture_request));
    }

//    public void reload() {
//        load(mLifecycleHandler.reload(R.id.day_picture_request));
//    }
//
//    private void load(Observable.Transformer<DayPicture, DayPicture> transformer) {
//        RepositoryProvider.provideNasaRepository()
//                .dayPicture()
//                .doOnSubscribe(() -> { setLoading(true); setError(false); })
//                .doOnTerminate(() -> setLoading(false))
//                .compose(transformer)
//                .subscribe(dayPicture -> {
//                    mDayPicture = dayPicture;
//                    notifyPropertyChanged(BR.title);
//                    notifyPropertyChanged(BR.explanation);
//                    notifyPropertyChanged(BR.url);
//                }, throwable -> setError(true));
//    }

    @Bindable
    @NonNull
    public String getDate() {
        if (mDayPicture == null) {
            return "";
        }
        return mDayPicture.getDate();
    }

    @Bindable
    @NonNull
    public String getTitle() {
        if (mDayPicture == null) {
            return "";
        }
        return mDayPicture.getTitle();
    }

    @Bindable
    @NonNull
    public String getExplanation() {
        if (mDayPicture == null) {
            return "";
        }
        return mDayPicture.getExplanation();
    }

    @Bindable
    @NonNull
    public String getUrl() {
        if (mDayPicture == null) {
            return "";
        }
        return mDayPicture.getUrl();
    }

    @Bindable
    public boolean isLoading() {
        return mIsLoading;
    }

    @VisibleForTesting
    void setLoading(boolean isLoading) {
        mIsLoading = isLoading;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public boolean isError() {
        return mIsError;
    }

    @VisibleForTesting
    void setError(boolean isError) {
        mIsError = isError;
        notifyPropertyChanged(BR.error);
    }
}
