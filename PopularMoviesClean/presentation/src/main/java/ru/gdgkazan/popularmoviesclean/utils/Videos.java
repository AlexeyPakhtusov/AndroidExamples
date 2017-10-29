package ru.gdgkazan.popularmoviesclean.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public final class Videos {

    private static final String YOUTUBE = "https://www.youtube.com/watch?v=";

    private Videos() {
    }

    public static void browseVideo(@NonNull Context context, @NonNull ru.gdgkazan.popularmoviesclean.data.model.content.Video video) {
        String videoUrl = YOUTUBE + video.getKey();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        context.startActivity(intent);
    }

    public static void browseVideo(@NonNull Context context, @NonNull ru.gdgkazan.popularmoviesclean.domain.model.Video video) {
        ru.gdgkazan.popularmoviesclean.data.model.content.Video localVideo =
                new ru.gdgkazan.popularmoviesclean.data.model.content.Video();
        localVideo.setKey(video.getKey());
        localVideo.setName(video.getName());
        localVideo.setMovieId(video.getMovieId());
        browseVideo(context, localVideo);
    }

}
