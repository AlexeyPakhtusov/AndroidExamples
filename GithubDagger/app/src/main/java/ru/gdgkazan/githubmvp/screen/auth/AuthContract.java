package ru.gdgkazan.githubmvp.screen.auth;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.githubmvp.screen.general.LoadingView;

/**
 * @author Alexey Pakhtusov
 */
public interface AuthContract {

    interface View extends LoadingView {

        @NonNull
        LifecycleHandler getLifecycleHandler();

        void openRepositoriesScreen();

        void showLoginError();

        void showPasswordError();

    }

    interface Presenter {

        void init(@NonNull View view);

        void tryLogIn(@NonNull String login, @NonNull String password);

    }

}
