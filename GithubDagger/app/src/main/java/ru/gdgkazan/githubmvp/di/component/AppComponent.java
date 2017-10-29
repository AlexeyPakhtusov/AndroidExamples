package ru.gdgkazan.githubmvp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.gdgkazan.githubmvp.di.module.ApiModule;
import ru.gdgkazan.githubmvp.di.module.DataBaseModule;
import ru.gdgkazan.githubmvp.di.module.ProdKeyValueStorageModule;
import ru.gdgkazan.githubmvp.di.module.ProdOkHttpClientModule;
import ru.gdgkazan.githubmvp.di.module.PresenterModule;
import ru.gdgkazan.githubmvp.repository.KeyValueStorage;

/**
 * @author Alexey Pakhtusov
 */
@Singleton
@Component(modules = { ApiModule.class,
                       DataBaseModule.class,
                       ProdOkHttpClientModule.class,
                       ProdKeyValueStorageModule.class })
public interface AppComponent {

    ViewComponent getViewComponent();

    KeyValueStorage keyValueStorage();
}
