package com.myst3ry.weekmovies.data;

import com.myst3ry.weekmovies.AppModule;
import com.myst3ry.weekmovies.ui.activity.MainActivity;
import com.myst3ry.weekmovies.ui.fragment.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DataModule.class, AppModule.class})
public interface DataComponent {

    void inject(MainActivity mainActivity);

    void inject(BaseFragment baseFragment);
}

