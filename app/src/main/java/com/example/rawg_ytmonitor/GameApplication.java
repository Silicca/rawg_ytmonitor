package com.example.rawg_ytmonitor;

import android.app.Application;

import com.example.rawg_ytmonitor.data.di.FakeDependencyInjection;
import com.facebook.stetho.Stetho;

public class GameApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}
