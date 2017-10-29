package ru.gdgkazan.popularmoviesclean.data.model.content;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * @author Alexey Pakhtusov
 */
public class Review extends RealmObject {

    @SerializedName("author")
    private String mAuthor;

    @SerializedName("content")
    private String mContent;

    private int mMovieId;

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getMovieId() {
        return mMovieId;
    }

    public void setMovieId(int movieId) {
        mMovieId = movieId;
    }
}
