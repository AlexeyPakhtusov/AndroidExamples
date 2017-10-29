package ru.gdgkazan.popularmoviesclean.data.cache;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.gdgkazan.popularmoviesclean.data.model.content.Review;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author Alexey Pakhtusov
 */
public class ReviewsCacheTransformer implements Observable.Transformer<List<Review>, List<Review>> {

    private int mMovieId;

    public ReviewsCacheTransformer(int movieId) {
        mMovieId = movieId;
    }

    private final Func1<List<Review>, Observable<List<Review>>> mSaveFunc = reviews -> {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            for (Review review : reviews) {
                review.setMovieId(mMovieId);
            }
            realm.where(Review.class)
                    .equalTo("mMovieId", mMovieId)
                    .findAll()
                    .deleteAllFromRealm();
            realm.insert(reviews);
        });
        return Observable.just(reviews);
    };

    private final Func1<Throwable, Observable<List<Review>>> mCacheErrorHandler = throwable -> {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Review> results = realm.where(Review.class)
                .equalTo("mMovieId", mMovieId)
                .findAll();
        return Observable.just(realm.copyFromRealm(results));
    };

    @Override
    public Observable<List<Review>> call(Observable<List<Review>> observable) {
        return observable
                .flatMap(mSaveFunc)
                .onErrorResumeNext(mCacheErrorHandler);
    }

}
