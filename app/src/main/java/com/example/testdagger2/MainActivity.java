package com.example.testdagger2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.testdagger2.Adapters.RandomUserAdapter;
import com.example.testdagger2.Modules.ContextModule;
import com.example.testdagger2.interfaces.DaggerRandomUsersComponent;
import com.example.testdagger2.interfaces.RandomUsersApi;
import com.example.testdagger2.interfaces.RandomUsersComponent;
import com.example.testdagger2.model.RandomUsers;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RandomUserAdapter mAdapter;

    RandomUsersApi randomUsersApi;
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        RandomUsersComponent component = DaggerRandomUsersComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        picasso = component.getPicasso();
        randomUsersApi = component.getRandomUsersService();
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