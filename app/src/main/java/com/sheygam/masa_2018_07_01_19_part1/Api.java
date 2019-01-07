package com.sheygam.masa_2018_07_01_19_part1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("/api/registration")
    Call<AuthResponseDto> registration(@Body AuthRequestDto body);
}
