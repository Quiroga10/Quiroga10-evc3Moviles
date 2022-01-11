package com.quiroga.sports_shop;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class SportApplication extends Application {
    private static SportApplication instance;
    private static Context appContext;

    public static SportApplication getInstance() {return instance; }

    public static Context getAppContext() { return appContext; }

    public void setAppContext(Context mAppContext) { this.appContext = mAppContext; }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
