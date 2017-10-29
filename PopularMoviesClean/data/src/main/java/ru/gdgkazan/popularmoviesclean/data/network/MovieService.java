package ru.gdgkazan.popularmoviesclean.data.network;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.gdgkazan.popularmoviesclean.data.model.response.MoviesResponse;
import ru.gdgkazan.popularmoviesclean.data.model.response.ReviewsResponse;
import ru.gdgkazan.popularmoviesclean.data.model.response.VideosResponse;
import rx.Observable;

/**
 * @author Alexey Pakhtusov
 */
public interface MovieService {

    @GET("popular/")
    Observable<MoviesResponse> popularMovies();

    @GET("{id}/videos")
    Observable<VideosResponse> getMovieTrailers(@Path("id") int movieId);

    @GET("{id}/reviews")
    Observable<ReviewsResponse> getMovieReviews(@Path("id") int movieId);

}
