package ru.gdgkazan.pictureofdaymvvm.screen.pictures;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.pictureofdaymvvm.R;
import ru.gdgkazan.pictureofdaymvvm.content.DayPicture;
import ru.gdgkazan.pictureofdaymvvm.utils.Images;

/**
 * @author Alexey Pakhtusov
 */
public class PicturesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageView)
    ImageView mImageView;

    @NonNull
    public static PicturesViewHolder create(@NonNull Context context, int imageWidth, int imageHeight) {
        View view = View.inflate(context, R.layout.picture_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = imageWidth;
        layoutParams.height = imageHeight;
        imageView.requestLayout();
        return new PicturesViewHolder(view);
    }

    public PicturesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull DayPicture dayPicture) {
        String url = dayPicture.getUrl();
        Images.load(mImageView, url);
        Images.fetch(url);
    }
}