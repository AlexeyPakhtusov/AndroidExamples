package ru.gdgkazan.popularmoviesclean.data.cache;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.gdgkazan.popularmoviesclean.data.model.content.Video;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author Alexey Pakhtusov
 */
public class VideosCacheTransformer implements Observable.Transformer<List<Video>, List<Video>> {

    private int mMovieId;

    public VideosCacheTransformer(int movieId) {
        mMovieId = movieId;
    }

    private final Func1<List<Video>, Observable<List<Video>>> mSaveFunc = videos -> {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            for (Video video : videos) {
                video.setMovieId(mMovieId);
            }
            realm.where(Video.class)
                    .equalTo("mMovieId", mMovieId)
                    .findAll()
                    .deleteAllFromRealm();
            realm.insert(videos);
        });
        return Observable.just(videos);
    };

    private final Func1<Throwable, Observable<List<Video>>> mCacheErrorHandler = throwable -> {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Video> results = realm.where(Video.class)
                .equalTo("mMovieId", mMovieId)
                .findAll();
        return Observable.just(realm.copyFromRealm(results));
    };

    @Override
    public Observable<List<Video>> call(Observable<List<Video>> observable) {
        return observable
                .flatMap(mSaveFunc)
                .onErrorResumeNext(mCacheErrorHandler);
    }

}
