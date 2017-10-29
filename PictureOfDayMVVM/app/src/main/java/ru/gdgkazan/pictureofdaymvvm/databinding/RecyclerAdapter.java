package ru.gdgkazan.pictureofdaymvvm.databinding;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.gdgkazan.pictureofdaymvvm.content.DayPicture;
import ru.gdgkazan.pictureofdaymvvm.screen.pictures.PicturesAdapter;

/**
 * @author Alexey Pakhtusov
 */
public class RecyclerAdapter {

    @SuppressWarnings("unchecked")
    /* Мы сами определяем имена атрибутов (app:имяАтрибута) для данного адаптера.
     * Данные имена мы указываем в xml разметке, а также указываем, какой метод должен вызыватся,
     * чтобы вернуть объект, который подставляется в параметры метода initRecycler
     * (в данном случае имя метода может быть любым).
     * Сначала идет View, в данном случае RecyclerView, потом все остальные имена атрибутов.
     * Например, мы в разметке указали, что для app:layoutManager (ПЕРВЫЙ элемент в списке value)
     * должен возвращаться RecyclerView.LayoutManager, тогда мы должны ПЕРВЫМ параметром
     * после RecyclerView указать RecyclerView.LayoutManager.
     * Далее идет app:adapter (ВТОРОЙ элемент в списке value), для которого в xml мы указали,
     * что вызывается метод, возвращающий PicturesAdapter, тогда ВТОРЫМ параметром после
     * RecyclerView мы указываем PicturesAdapter и так далее:
     * для app:items - List<DayPicture>,
     * для app:scrollListener - RecyclerView.OnScrollListener,
     * для app:onItemClick - PicturesAdapter.OnItemClickListener. */
    @BindingAdapter(value = { "app:layoutManager",
                              "app:adapter",
                              "app:items",
                              "app:scrollListener",
                              "app:onItemClick" },
                    requireAll = false)
    public static void initRecycler(@NonNull RecyclerView recycler,
                                    @NonNull RecyclerView.LayoutManager layoutManager,
                                    @NonNull PicturesAdapter adapter,
                                    @NonNull List<DayPicture> items,
                                    @Nullable RecyclerView.OnScrollListener scrollListener,
                                    @Nullable PicturesAdapter.OnItemClickListener itemClickListener) {
        if (recycler.getLayoutManager() == null) {
            recycler.setLayoutManager(layoutManager);
        }
        if (recycler.getAdapter() == null) {
            adapter.attachToRecyclerView(recycler);
        } else {
            ((PicturesAdapter) recycler.getAdapter()).changeDataSet(items);
        }
        if (scrollListener != null) {
            recycler.addOnScrollListener(scrollListener);
        }
        if (itemClickListener != null) {
            adapter.setOnItemClickListener(itemClickListener);
        }
        recycler.setNestedScrollingEnabled(false);
    }
}
