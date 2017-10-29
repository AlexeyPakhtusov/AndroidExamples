package ru.gdgkazan.popularmoviesclean.domain.model;

/**
 * @author Alexey Pakhtusov
 */
public class Review {

    private String mAuthor;
    private String mContent;
    private int mMovieId;

    public Review() { }

    public Review(String author, String content, int movieId) {
        mAuthor = author;
        mContent = content;
        mMovieId = movieId;
    }

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
