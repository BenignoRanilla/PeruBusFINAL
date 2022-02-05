package com.ruta.perubus.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("Usuario/IniciarSesion/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
