package ru.gdgkazan.githubmvp.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gdgkazan.githubmvp.repository.KeyValueStorage;
import ru.gdgkazan.githubmvp.repository.TestHawkKeyValueStorage;

/**
 * @author Alexey Pakhtusov
 */
@Module
public class TestKeyValueStorageModule {

    @Provides
    @Singleton
    KeyValueStorage provideKeyValueStorage() {
        return new TestHawkKeyValueStorage();
    }
}
