package ru.gdgkazan.githubmvp.screen.auth;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.githubmvp.R;
import ru.gdgkazan.githubmvp.repository.GithubRepository;
import ru.gdgkazan.githubmvp.repository.KeyValueStorage;
import ru.gdgkazan.githubmvp.utils.TextUtils;

/**
 * @author Alexey Pakhtusov
 */
public class AuthPresenter implements AuthContract.Presenter {

    private final GithubRepository mRepository;

    private final KeyValueStorage mKeyValueStorage;

    private LifecycleHandler mLifecycleHandler;

    private AuthContract.View mView;

    @Inject
    public AuthPresenter(@NonNull GithubRepository repository,
                         @NonNull KeyValueStorage keyValueStorage) {
        mRepository = repository;
        mKeyValueStorage = keyValueStorage;
    }

    @Override
    public void init(@NonNull AuthContract.View view) {
        mView = view;
        mLifecycleHandler = view.getLifecycleHandler();
        String token = mKeyValueStorage.getToken();
        if (!TextUtils.isEmpty(token)) {
            mView.openRepositoriesScreen();
        }
    }

    @Override
    public void tryLogIn(@NonNull String login, @NonNull String password) {
        if (TextUtils.isEmpty(login)) {
            mView.showLoginError();
        } else if (TextUtils.isEmpty(password)) {
            mView.showPasswordError();
        } else {
            mRepository.auth(login, password)
                    .doOnSubscribe(mView::showLoading)
                    .doOnTerminate(mView::hideLoading)
                    .compose(mLifecycleHandler.reload(R.id.auth_request))
                    .subscribe(authorization -> mView.openRepositoriesScreen(),
                            throwable -> mView.showLoginError());
        }
    }

}
