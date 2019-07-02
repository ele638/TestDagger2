package com.example.testdagger2.Modules;

import com.example.testdagger2.Interfaces.RandomUsersApi;
import com.example.testdagger2.Interfaces.Scopes.RandomUsersApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class RandomUsersModule {

    @Provides
    public RandomUsersApi getRandomUserAPI(Retrofit retrofit) {
        return retrofit.create(RandomUsersApi.class);
    }

    @RandomUsersApplicationScope
    @Provides
    Retrofit retrofit(OkHttpClient okHttpClient,
                      GsonConverterFactory gsonConverterFactory,
                      Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }
}
