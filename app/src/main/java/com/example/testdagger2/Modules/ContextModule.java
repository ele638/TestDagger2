package com.example.testdagger2.Modules;

import android.content.Context;

import com.example.testdagger2.Interfaces.Scopes.ApplicationContext;
import com.example.testdagger2.Interfaces.Scopes.RandomUsersApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @ApplicationContext
    @RandomUsersApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
