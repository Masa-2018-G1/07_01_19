package com.sheygam.masa_2018_07_01_19_part1;

public class LoginInteractor implements AuthInteractor {
    private AuthRepository repository;

    public LoginInteractor(AuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registration(String email, String password, final AuthInteractorCallback callback) {
        if(!email.contains("@") || password.length() < 8){
            throw new IllegalArgumentException("Wrong email or password");
        }
        repository.registration(email, password, new AuthRepositoryCallback() {
            @Override
            public void registrationSuccess() {
                callback.registrationSuccess();
            }

            @Override
            public void registrationFailure(String error) {
                callback.registrationFailure(error);
            }
        });
    }
}
