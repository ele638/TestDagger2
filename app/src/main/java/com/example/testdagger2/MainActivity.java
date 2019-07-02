package com.example.testdagger2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.testdagger2.Adapters.RandomUserAdapter;
import com.example.testdagger2.Modules.MainActivityModule;
import com.example.testdagger2.interfaces.DaggerMainActivityComponent;
import com.example.testdagger2.interfaces.MainActivityComponent;
import com.example.testdagger2.interfaces.RandomUsersApi;
import com.example.testdagger2.model.RandomUsers;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Inject
    RandomUserAdapter mAdapter;

    @Inject
    RandomUsersApi randomUsersApi;

    @Inject
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .randomUsersComponent(RandomUserApplication.get(this).getRandomUsersApplicationComponent())
                .build();

        component.injectMainActivity(this);
        populateUsers();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateUsers() {
        Call<RandomUsers> randomUsersCall = randomUsersApi.getRandomUsers(10);
        randomUsersCall.enqueue(new Callback<RandomUsers>() {
            @Override
            public void onResponse(@NotNull Call<RandomUsers> call, @NonNull Response<RandomUsers> response) {
                if (response.isSuccessful()) {
                    mAdapter = new RandomUserAdapter(picasso);
                    if (response.body() != null) {
                        mAdapter.setItems(response.body().getResults());
                    }
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<RandomUsers> call, @NotNull Throwable t) {
                Timber.i(t);
            }
        });
    }


}