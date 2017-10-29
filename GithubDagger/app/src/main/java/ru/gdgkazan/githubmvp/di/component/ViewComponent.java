package ru.gdgkazan.githubmvp.di.component;

import dagger.Subcomponent;
import ru.gdgkazan.githubmvp.di.module.PresenterModule;
import ru.gdgkazan.githubmvp.di.scope.ViewScope;
import ru.gdgkazan.githubmvp.screen.auth.AuthActivity;
import ru.gdgkazan.githubmvp.screen.repositories.RepositoriesActivity;
import ru.gdgkazan.githubmvp.screen.walkthrough.WalkthroughActivity;

/**
 * @author Alexey Pakhtusov
 */
@ViewScope
@Subcomponent(modules = { PresenterModule.class })
public interface ViewComponent {

    void injectAuthActivity(AuthActivity authActivity);

    void injectRepositoriesActivity(RepositoriesActivity repositoriesActivity);

    void injectWalkthroughActivity(WalkthroughActivity walkthroughActivity);
}
