package ru.gdgkazan.githubmvp.di.module;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.gdgkazan.githubmvp.BuildConfig;
import ru.gdgkazan.githubmvp.api.GithubService;
import ru.gdgkazan.githubmvp.repository.DefaultGithubRepository;
import ru.gdgkazan.githubmvp.repository.GithubRepository;
import ru.gdgkazan.githubmvp.repository.KeyValueStorage;

/**
 * @author Alexey Pakhtusov
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    GithubRepository provideGithubRepository(@NonNull GithubService githubService,
                                             @NonNull KeyValueStorage keyValueStorage) {
        return new DefaultGithubRepository(githubService, keyValueStorage);
    }

    @Provides
    @Singleton
    GithubService provideGithubService(@NonNull Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
