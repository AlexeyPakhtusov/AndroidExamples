package ru.gdgkazan.pictureofdaymvvm.utils;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ru.gdgkazan.pictureofdaymvvm.DayPictureApp;
import ru.gdgkazan.pictureofdaymvvm.content.DayPicture;

/**
 * @author Alexey Pakhtusov
 */
public class Images {

    public static void load(@NonNull ImageView imageView, @NonNull String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
    }

    public static void fetch(@NonNull String url) {
        Picasso.with(DayPictureApp.getAppContext())
                .load(url)
                .fetch();
    }
}
