package com.ruta.perubus.api

import android.telecom.Call
import com.ruta.perubus.models.LoggedInUser
import com.ruta.perubus.models.User
import retrofit2.http.*
import java.lang.StringBuilder

interface Api {

    @POST("Usuario/RegistrarUsuario")
    fun createUser(@Body newUser: User): retrofit2.Call<User>

    @POST("Usuario/IniciarSesion")
    fun userLogin(
        @Field("funcion") function: String,
        @Field("NroCelular") NroCelular: String,
        @Field("Contrasenia") Contrasenia: String
    ): retrofit2.Call<String>
}