package ru.gdgkazan.githubmvp.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import ru.gdgkazan.githubmvp.api.ApiKeyInterceptor;
import ru.gdgkazan.githubmvp.api.LoggingInterceptor;

/**
 * @author Alexey Pakhtusov
 */
@Module
public class ProdOkHttpClientModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor.create())
                .addInterceptor(ApiKeyInterceptor.create())
                .build();
    }
}
