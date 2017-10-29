package ru.gdgkazan.githubmvp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.gdgkazan.githubmvp.di.module.ApiModule;
import ru.gdgkazan.githubmvp.di.module.DataBaseModule;
import ru.gdgkazan.githubmvp.di.module.TestKeyValueStorageModule;
import ru.gdgkazan.githubmvp.di.module.TestOkHttpClientModule;

/**
 * @author Alexey Pakhtusov
 */
@Singleton
@Component(modules = { ApiModule.class,
                       DataBaseModule.class,
                       TestOkHttpClientModule.class,
                       TestKeyValueStorageModule.class })
public interface TestAppComponent extends AppComponent { }
