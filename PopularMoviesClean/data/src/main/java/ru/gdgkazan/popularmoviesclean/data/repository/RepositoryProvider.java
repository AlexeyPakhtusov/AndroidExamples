package ru.gdgkazan.popularmoviesclean.data.repository;

import ru.gdgkazan.popularmoviesclean.domain.MovieDetailsRepository;
import ru.gdgkazan.popularmoviesclean.domain.MoviesRepository;

/**
 * @author Artur Vasilov
 */
public class RepositoryProvider {

    private static MoviesRepository sMoviesRepository;
    private static MovieDetailsRepository sMovieDetailsRepository;


    public static MoviesRepository getMoviesRepository() {
        if (sMoviesRepository == null) {
            sMoviesRepository = new MoviesDataRepository();
        }
        return sMoviesRepository;
    }

    public static MovieDetailsRepository getMovieDetailsRepository() {
        if (sMovieDetailsRepository == null) {
            sMovieDetailsRepository = new MovieDetailsDataRepository();
        }
        return sMovieDetailsRepository;
    }

    public static void setMoviesRepository(MoviesRepository moviesRepository) {
        sMoviesRepository = moviesRepository;
    }

    public static void setMovieDetailRepository(MovieDetailsRepository movieDetailsRepository) {
        sMovieDetailsRepository = movieDetailsRepository;
    }
}
