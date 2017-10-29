package ru.gdgkazan.popularmoviesclean.domain.model;

/**
 * @author Alexey Pakhtusov
 */
public class Video {

    private String mKey;
    private String mName;
    private int mMovieId;

    public Video() { }

    public Video(String key, String name, int movieId) {
        mKey = key;
        mName = name;
        mMovieId = movieId;
    }

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
