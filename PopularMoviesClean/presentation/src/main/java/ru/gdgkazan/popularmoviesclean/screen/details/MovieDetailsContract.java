package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.domain.model.Review;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;
import ru.gdgkazan.popularmoviesclean.screen.general.LoadingView;

/**
 * @author Alexey Pakhtusov
 */
public interface MovieDetailsContract {

    interface MovieDetailsView extends LoadingView {
        void showTrailers(@NonNull List<Video> videos);

        void showReviews(@NonNull List<Review> reviews);

        void showError();
    }

    interface MovieDetailsPresenter { }

}
