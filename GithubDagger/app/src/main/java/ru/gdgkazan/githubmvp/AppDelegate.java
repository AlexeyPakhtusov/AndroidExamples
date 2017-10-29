package ru.gdgkazan.githubmvp;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;
import ru.gdgkazan.githubmvp.di.component.AppComponent;
import ru.gdgkazan.githubmvp.di.component.DaggerAppComponent;
import ru.gdgkazan.githubmvp.di.component.ViewComponent;

/**
 * @author Alexey Pakhtusov
 */
public class AppDelegate extends Application {

    /**
     * TODO : task
     *
     * Create modules and subcomponents for each screen
     * Inject Presenters for each screen
     * Use test modules to test your app
     */

    private static Context sContext = null;

    private static AppComponent sAppComponent = null;

    private static ViewComponent sViewComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;

        Hawk.init(getAppContext())
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(getAppContext()))
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .build();

        RealmConfiguration configuration = new RealmConfiguration.Builder(getAppContext())
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);

        sAppComponent = DaggerAppComponent.create();
    }

    public static Context getAppContext() {
        return sContext;
    }

    @NonNull
    public static AppComponent getAppComponent() {
        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.create();
        }

        return sAppComponent;
    }

    public static void setAppComponent(@Nullable AppComponent appComponent) {
        sAppComponent = appComponent;
    }

    @NonNull
    public static ViewComponent getViewComponent() {
        if (sViewComponent == null) {
            sViewComponent = getAppComponent().getViewComponent();
        }

        return sViewComponent;
    }

    public static void setViewComponent(@Nullable ViewComponent viewComponent) {
        sViewComponent = viewComponent;
    }
}
