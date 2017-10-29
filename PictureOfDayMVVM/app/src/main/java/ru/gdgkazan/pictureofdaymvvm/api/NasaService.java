package ru.gdgkazan.pictureofdaymvvm.api;

import android.support.annotation.NonNull;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.gdgkazan.pictureofdaymvvm.content.DayPicture;
import rx.Observable;

/**
 * @author Alexey Pakhtusov
 */
public interface NasaService {

    @GET("/planetary/apod")
    Observable<DayPicture> todayPicture();

    @GET("/planetary/apod")
    Observable<DayPicture> datePicture(@NonNull @Query("date") String date);
}
