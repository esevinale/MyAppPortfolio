package com.esevinale.myappportfolio.application;

import android.app.Application;
import android.content.Context;

import com.esevinale.myappportfolio.BuildConfig;
import com.esevinale.myappportfolio.application.builder.AppComponent;
import com.esevinale.myappportfolio.application.builder.AppModule;
import com.esevinale.myappportfolio.application.builder.DaggerAppComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class AppController extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration;
        if (BuildConfig.DEBUG)
            realmConfiguration = new RealmConfiguration
                    .Builder()
                    .inMemory()
                    .name("test")
                    .deleteRealmIfMigrationNeeded()
                    .build();
        else
            realmConfiguration = new RealmConfiguration
                    .Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
