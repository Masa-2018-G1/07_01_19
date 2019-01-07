package com.sheygam.masa_2018_07_01_19_part1;

public interface AuthRepository {
    void registration(String email, String password, AuthRepositoryCallback callback);
}
