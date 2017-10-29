package ru.gdgkazan.popularmoviesclean.screen.details;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.popularmoviesclean.R;
import ru.gdgkazan.popularmoviesclean.domain.usecase.MovieDetailsUseCase;

/**
 * @author Alexey Pakhtusov
 */
public class MovieDetailsPresenter implements MovieDetailsContract.MovieDetailsPresenter {

    private MovieDetailsContract.MovieDetailsView mMovieDetailsView;
    private MovieDetailsUseCase mMovieDetailsUseCase;
    private LifecycleHandler mLifecycleHandler;

    public MovieDetailsPresenter(MovieDetailsContract.MovieDetailsView movieDetailsView,
                                 MovieDetailsUseCase moviesUseCase,
                                 LifecycleHandler lifecycleHandler) {
        mMovieDetailsView = movieDetailsView;
        mMovieDetailsUseCase = moviesUseCase;
        mLifecycleHandler = lifecycleHandler;
    }

    public void init(int movieId) {
        mMovieDetailsUseCase.getMovieTrailers(movieId)
                .doOnSubscribe(mMovieDetailsView::showLoadingIndicator)
                .doAfterTerminate(mMovieDetailsView::hideLoadingIndicator)
                .compose(mLifecycleHandler.load(R.id.movie_details_trailers_request_id))
                .subscribe(mMovieDetailsView::showTrailers, throwable -> mMovieDetailsView.showError());
        mMovieDetailsUseCase.getMovieReviews(movieId)
                .doOnSubscribe(mMovieDetailsView::showLoadingIndicator)
                .doAfterTerminate(mMovieDetailsView::hideLoadingIndicator)
                .compose(mLifecycleHandler.load(R.id.movie_details_reviews_request_id))
                .subscribe(mMovieDetailsView::showReviews, throwable -> mMovieDetailsView.showError());
    }

}
