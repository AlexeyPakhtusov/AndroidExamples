package ru.gdgkazan.popularmoviesclean.data.repository;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.data.cache.ReviewsCacheTransformer;
import ru.gdgkazan.popularmoviesclean.data.cache.VideosCacheTransformer;
import ru.gdgkazan.popularmoviesclean.data.mapper.ReviewsMapper;
import ru.gdgkazan.popularmoviesclean.data.mapper.VideosMapper;
import ru.gdgkazan.popularmoviesclean.data.model.response.ReviewsResponse;
import ru.gdgkazan.popularmoviesclean.data.model.response.VideosResponse;
import ru.gdgkazan.popularmoviesclean.data.network.ApiFactory;
import ru.gdgkazan.popularmoviesclean.domain.model.Review;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;
import rx.Observable;

/**
 * @author Alexey Pakhtusov
 */
public class MovieDetailsDataRepository implements ru.gdgkazan.popularmoviesclean.domain.MovieDetailsRepository {

    @Override
    public Observable<List<Video>> getMovieTrailers(int movieId) {
        return ApiFactory.getMoviesService()
                .getMovieTrailers(movieId)
                .map(VideosResponse::getVideos)
                .compose(new VideosCacheTransformer(movieId))
                .flatMap(Observable::from)
                .map(new VideosMapper())
                .toList();
    }

    @Override
    public Observable<List<Review>> getMovieReviews(int movieId) {
        return ApiFactory.getMoviesService()
                .getMovieReviews(movieId)
                .map(ReviewsResponse::getReviews)
                .compose(new ReviewsCacheTransformer(movieId))
                .flatMap(Observable::from)
                .map(new ReviewsMapper())
                .toList();
    }

}
