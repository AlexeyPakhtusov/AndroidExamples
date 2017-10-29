package ru.gdgkazan.popularmoviesclean.data.mapper;

import ru.gdgkazan.popularmoviesclean.domain.model.Review;
import rx.functions.Func1;

/**
 * @author Alexey Pakhtusov
 */
public class ReviewsMapper implements Func1<ru.gdgkazan.popularmoviesclean.data.model.content.Review, Review> {

    @Override
    public Review call(ru.gdgkazan.popularmoviesclean.data.model.content.Review review) {
        return new Review(review.getAuthor(), review.getContent(), review.getMovieId());
    }

}
