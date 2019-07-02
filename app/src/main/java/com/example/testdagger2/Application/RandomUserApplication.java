package com.example.testdagger2.Application;

import android.app.Activity;
import android.app.Application;

import com.example.testdagger2.Interfaces.Components.DaggerRandomUsersComponent;
import com.example.testdagger2.Interfaces.Components.RandomUsersComponent;
import com.example.testdagger2.Modules.ContextModule;

import timber.log.Timber;

public class RandomUserApplication extends Application {

    private RandomUsersComponent randomUsersApplicationComponent;

    public static RandomUserApplication get(Activity activity) {
        return (RandomUserApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        randomUsersApplicationComponent = null;

        randomUsersApplicationComponent = DaggerRandomUsersComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public RandomUsersComponent getRandomUsersApplicationComponent() {
        return randomUsersApplicationComponent;
    }
}
