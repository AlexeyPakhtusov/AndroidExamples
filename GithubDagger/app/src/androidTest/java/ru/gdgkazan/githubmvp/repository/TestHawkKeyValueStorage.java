package ru.gdgkazan.githubmvp.repository;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * @author Alexey Pakhtusov
 */
public class TestHawkKeyValueStorage implements KeyValueStorage {

    private static final String TEST_TOKEN = "";
    private static final String TEST_USER_NAME = "";

    @Override
    public void saveToken(@NonNull String token) {
        // do nothing
    }

    @NonNull
    @Override
    public String getToken() {
        return TEST_TOKEN;
    }

    @Override
    public void saveUserName(@NonNull String userName) {
        // do nothing
    }

    @NonNull
    @Override
    public Observable<String> getUserName() {
        return Observable.just(TEST_USER_NAME);
    }

    @Override
    public void saveWalkthroughPassed() {
        // do nothing
    }

    @Override
    public boolean isWalkthroughPassed() {
        return false;
    }

    @Override
    public void clear() {
        // do nothing
    }
}
