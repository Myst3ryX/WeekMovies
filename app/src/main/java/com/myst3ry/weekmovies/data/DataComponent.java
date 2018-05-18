package com.myst3ry.weekmovies.data;

import com.myst3ry.weekmovies.AppModule;
import com.myst3ry.weekmovies.ui.activity.MainActivity;
import com.myst3ry.weekmovies.ui.fragment.BaseFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {AppModule.class, DataModule.class})
public interface DataComponent {

    void inject(MainActivity mainActivity);

    void inject(BaseFragment baseFragment);
}
