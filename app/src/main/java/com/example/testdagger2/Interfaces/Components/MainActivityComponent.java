package com.example.testdagger2.Interfaces.Components;

import com.example.testdagger2.Activities.MainActivity;
import com.example.testdagger2.Interfaces.Scopes.MainActivityScope;
import com.example.testdagger2.Modules.MainActivityModule;

import dagger.Component;

@Component(dependencies = RandomUsersComponent.class, modules = MainActivityModule.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivity activity);
}
