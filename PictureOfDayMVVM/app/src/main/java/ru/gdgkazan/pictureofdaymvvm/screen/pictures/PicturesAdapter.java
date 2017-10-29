package ru.gdgkazan.pictureofdaymvvm.screen.pictures;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gdgkazan.pictureofdaymvvm.content.DayPicture;

/**
 * @author Alexey Pakhtusov
 */
public class PicturesAdapter extends RecyclerView.Adapter<PicturesViewHolder> {

    private List<DayPicture> mDayPictures = new ArrayList<>();

    private int mImageWidth, mImageHeight;

    @Nullable
    private OnItemClickListener mOnItemClickListener;

    private View.OnClickListener mOnClickListener = (view) -> {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (DayPicture) view.getTag());
        }
    };

    @Nullable
    private RecyclerView mRecyclerView;

    public PicturesAdapter(@NonNull List<DayPicture> pictures, int imageWidth, int imageHeight) {
        mDayPictures.addAll(pictures);
        mImageWidth = imageWidth;
        mImageHeight = imageHeight;
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void attachToRecyclerView(@NonNull RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setAdapter(this);
        notifyDataSetChanged();
    }

    public void changeDataSet(@NonNull List<DayPicture> dayPictures) {
        mDayPictures.clear();
        mDayPictures.addAll(dayPictures);
        Collections.sort(mDayPictures, (x, y) -> x.getDate().compareTo(y.getDate()));
        notifyDataSetChanged();
    }

    @Override
    public PicturesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PicturesViewHolder.create(parent.getContext(), mImageWidth, mImageHeight);
    }

    @Override
    public void onBindViewHolder(PicturesViewHolder holder, int position) {
        DayPicture dayPicture = mDayPictures.get(position);
        holder.bind(dayPicture);
        holder.itemView.setTag(dayPicture);
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mDayPictures.size();
    }

    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull DayPicture dayPicture);
    }
}
