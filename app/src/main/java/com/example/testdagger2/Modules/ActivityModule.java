package com.example.testdagger2.Modules;

import android.content.Context;

import com.example.testdagger2.interfaces.ApplicationContext;
import com.example.testdagger2.interfaces.RandomUsersApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Context context;

    ActivityModule(Context context) {
        this.context = context;
    }

    @ApplicationContext
    @RandomUsersApplicationScope
    @Provides
    public Context context() {
        return context;
    }
}
