package ru.gdgkazan.popularmoviesclean.domain;

import java.util.List;

import rx.Observable;

/**
 * @author Alexey Pakhtusov
 */
public interface MovieDetailsRepository {

    Observable<List<ru.gdgkazan.popularmoviesclean.domain.model.Video>> getMovieTrailers(int movieId);

    Observable<List<ru.gdgkazan.popularmoviesclean.domain.model.Review>> getMovieReviews(int movieId);

}
