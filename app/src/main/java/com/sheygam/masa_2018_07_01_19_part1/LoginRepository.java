package com.sheygam.masa_2018_07_01_19_part1;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository implements AuthRepository {
    private Api api;
    private Store store;

    public LoginRepository(Api api, Store store) {
        this.api = api;
        this.store = store;
    }

    @Override
    public void registration(@NonNull String email, @NonNull String password, @NonNull final AuthRepositoryCallback callback) {
        AuthRequestDto body = new AuthRequestDto();
        body.email = email;
        body.password = password;
        api.registration(body).enqueue(new Callback<AuthResponseDto>() {
            @Override
            public void onResponse(Call<AuthResponseDto> call, Response<AuthResponseDto> response) {
                if(response.isSuccessful()){
                    store.token(response.body().token);
                    callback.registrationSuccess();
                }else if(response.code() == 409){
                    callback.registrationFailure("User already exist");
                }else {
                    callback.registrationFailure("Server error!");
                }
            }

            @Override
            public void onFailure(Call<AuthResponseDto> call, Throwable t) {
                callback.registrationFailure("Connection error!");
            }
        });
    }
}
