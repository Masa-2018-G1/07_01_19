package com.sheygam.masa_2018_07_01_19_part1;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {MainModule.class})
@Singleton
public interface AppComponent {
    LoginComponent plus(LoginModule module);
}
