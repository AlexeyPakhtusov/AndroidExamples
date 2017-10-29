package ru.gdgkazan.popularmoviesclean.data.model.content;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * @author Alexey Pakhtusov
 */
public class Video extends RealmObject {

    @SerializedName("key")
    private String mKey;

    @SerializedName("name")
    private String mName;

    private int mMovieId;

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getMovieId() {
        return mMovieId;
    }

    public void setMovieId(int movieId) {
        mMovieId = movieId;
    }
}
