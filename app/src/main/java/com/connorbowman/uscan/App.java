package com.connorbowman.uscan;

import android.app.Application;

import com.connorbowman.uscan.services.ApiService;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Create new API service
        new ApiService(getApplicationContext());
    }
}
