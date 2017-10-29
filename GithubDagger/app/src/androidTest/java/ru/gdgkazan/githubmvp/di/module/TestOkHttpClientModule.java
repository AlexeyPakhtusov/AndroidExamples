package ru.gdgkazan.githubmvp.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import ru.gdgkazan.githubmvp.api.TestInterceptor;

/**
 * @author Alexey Pakhtusov
 */
@Module
public class TestOkHttpClientModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(TestInterceptor.create())
                .build();
    }
}
