package ru.gdgkazan.githubmvp.screen.repositories;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.githubmvp.content.Repository;
import ru.gdgkazan.githubmvp.screen.general.LoadingView;

/**
 * @author Alexey Pakhtusov
 */
public interface RepositoriesContract {

    interface View extends LoadingView {

        @NonNull
        LifecycleHandler getLifecycleHandler();

        void showRepositories(@NonNull List<Repository> repositories);

        void showCommits(@NonNull Repository repository);

        void showError();

    }

    interface Presenter {

        void init(@NonNull View view);

        void onItemClick(@NonNull Repository repository);

    }

}
