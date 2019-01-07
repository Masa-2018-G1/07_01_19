package com.sheygam.masa_2018_07_01_19_part1;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @LoginScope
    AuthRepository provideAuthRepo(Store store, Api api){
        return new LoginRepository(api,store);
    }

    @Provides
    @LoginScope
    AuthInteractor provideAuthInteractor(AuthRepository repository){
        return new LoginInteractor(repository);
    }
}
