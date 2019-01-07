package com.sheygam.masa_2018_07_01_19_part1;

import dagger.Subcomponent;

@Subcomponent(modules = {LoginModule.class})
@LoginScope
public interface LoginComponent {
    void inject(MainActivity activity);
}
