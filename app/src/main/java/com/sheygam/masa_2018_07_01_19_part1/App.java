package com.sheygam.masa_2018_07_01_19_part1;

import android.app.Application;

public class App extends Application {
    private AppComponent component;
    private LoginComponent loginComponent;

    private static App app;

    public static App get(){
        return app;
    }

    public App() {
        app = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .mainModule(new MainModule(this))
                .build();
    }

    public LoginComponent loginComponent(){
        if(loginComponent == null){
            loginComponent = component.plus(new LoginModule());
        }
        return loginComponent;
    }

    public void clearLoginComponent(){
        loginComponent = null;
    }
}
