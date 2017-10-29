package ru.gdgkazan.githubmvp.di.module;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.gdgkazan.githubmvp.di.scope.ViewScope;
import ru.gdgkazan.githubmvp.repository.GithubRepository;
import ru.gdgkazan.githubmvp.repository.KeyValueStorage;
import ru.gdgkazan.githubmvp.screen.auth.AuthContract;
import ru.gdgkazan.githubmvp.screen.auth.AuthPresenter;
import ru.gdgkazan.githubmvp.screen.repositories.RepositoriesContract;
import ru.gdgkazan.githubmvp.screen.repositories.RepositoriesPresenter;
import ru.gdgkazan.githubmvp.screen.walkthrough.WalkthroughContract;
import ru.gdgkazan.githubmvp.screen.walkthrough.WalkthroughPresenter;

/**
 * @author Alexey Pakhtusov
 */
@Module
public class PresenterModule {

    @Provides
    @ViewScope
    AuthContract.Presenter provideAuthPresenter(@NonNull GithubRepository repository,
                                                @NonNull KeyValueStorage keyValueStorage) {
        return new AuthPresenter(repository, keyValueStorage);
    }

    @Provides
    @ViewScope
    RepositoriesContract.Presenter provideRepositoriesPresenter(@NonNull GithubRepository repository) {
        return new RepositoriesPresenter(repository);
    }

    @Provides
    @ViewScope
    WalkthroughContract.Presenter provideWalkthroughPresenter(@NonNull KeyValueStorage keyValueStorage) {
        return new WalkthroughPresenter(keyValueStorage);
    }
}
