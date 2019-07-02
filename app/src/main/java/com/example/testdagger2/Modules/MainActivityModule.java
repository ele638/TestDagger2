package com.example.testdagger2.Modules;

import com.example.testdagger2.Activities.MainActivity;
import com.example.testdagger2.Adapters.RandomUserAdapter;
import com.example.testdagger2.Interfaces.Scopes.MainActivityScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @MainActivityScope
    RandomUserAdapter adapter(Picasso picasso) {
        return new RandomUserAdapter(picasso);
    }
}
