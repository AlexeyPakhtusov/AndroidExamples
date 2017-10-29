package ru.gdgkazan.popularmoviesclean.domain.usecase;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.domain.MovieDetailsRepository;
import ru.gdgkazan.popularmoviesclean.domain.model.Review;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;
import rx.Observable;

/**
 * @author Alexey Pakhtusov
 */
public class MovieDetailsUseCase {

    private final MovieDetailsRepository mRepository;
    private final Observable.Transformer<List<Video>, List<Video>> mAsyncTransformerVideos;
    private final Observable.Transformer<List<Review>, List<Review>> mAsyncTransformerReviews;

    public MovieDetailsUseCase(MovieDetailsRepository repository,
                               Observable.Transformer<List<Video>, List<Video>> asyncTransformerVideos,
                               Observable.Transformer<List<Review>, List<Review>> asyncTransformerReviews) {
        mRepository = repository;
        mAsyncTransformerVideos = asyncTransformerVideos;
        mAsyncTransformerReviews = asyncTransformerReviews;
    }

    public Observable<List<Video>> getMovieTrailers(int movieId) {
        return mRepository.getMovieTrailers(movieId)
                .compose(mAsyncTransformerVideos);
    }

    public Observable<List<Review>> getMovieReviews(int movieId) {
        return mRepository.getMovieReviews(movieId)
                .compose(mAsyncTransformerReviews);
    }

}
