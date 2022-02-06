package com.ruta.perubus.api

import android.telecom.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.lang.StringBuilder

interface Api {

    @FormUrlEncoded
    @POST("Usuario/RegistrarUsuario")
    fun createUser(
        @Field("Contrasenia") Contrasenia:String,
        @Field("Nombres") Nombres:String,
        @Field("Apellidos") Apellidos:String,
        @Field("Correo") Correo:String,
        @Field("NroCelular") NroCelular:String,
        @Field("Origen") Origen:String
        )

    @FormUrlEncoded
    @POST("/Usuario/IniciarSesion")
    fun userLogin(
        @Field("NroCelular") NroCelular:String,
        @Field("Contrasenia") Contrasenia:String
    )
}