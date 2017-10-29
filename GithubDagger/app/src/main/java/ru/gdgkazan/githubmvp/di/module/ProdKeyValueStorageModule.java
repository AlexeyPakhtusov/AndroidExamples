package ru.gdgkazan.githubmvp.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gdgkazan.githubmvp.repository.HawkKeyValueStorage;
import ru.gdgkazan.githubmvp.repository.KeyValueStorage;

/**
 * @author Alexey Pakhtusov
 */
@Module
public class ProdKeyValueStorageModule {

    @Provides
    @Singleton
    KeyValueStorage provideKeyValueStorage() {
        return new HawkKeyValueStorage();
    }
}
