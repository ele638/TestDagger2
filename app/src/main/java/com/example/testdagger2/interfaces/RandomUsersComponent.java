package com.example.testdagger2.interfaces;

import com.example.testdagger2.Modules.PicassoModule;
import com.example.testdagger2.Modules.RandomUsersModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@RandomUsersApplicationScope
@Component(modules = {RandomUsersModule.class, PicassoModule.class})
public interface RandomUsersComponent {
    RandomUsersApi getRandomUsersService();

    Picasso getPicasso();
}
