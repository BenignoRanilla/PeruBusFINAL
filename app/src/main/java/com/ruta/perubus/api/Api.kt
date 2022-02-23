package com.ruta.perubus.api

import android.telecom.Call
import com.ruta.perubus.models.*
import retrofit2.http.*
import java.lang.StringBuilder

interface Api {

    @POST("Usuario/RegistrarUsuario")
    fun createUser(@Body newUser: User): retrofit2.Call<User>

    @POST("/Usuario/IniciarSesion")
    fun userLogin(@Body newSession: LoggedInUser): retrofit2.Call<LoggedInUser>

    @PUT("Usuario/ModificarUsuario/{CoUsua}")
    fun resetPassword(@Body reset: ResetPass): retrofit2.Call<ResetPass>

    @PUT ("Usuario/ModificarUsuario/{CoUsua}")
    fun editProfile(@Body editUser: EditUser): retrofit2.Call<EditUser>

    @POST("/TdprogItin/ObtenerItinerario/{GPSOrigen}")
    fun ObtenerItinerario(@Body newItinerario: Itinerario): retrofit2.Call<Itinerario>

    @GET("/Tramos/ObtenerOrigen")
    fun ObtenerOrigen(@Body newOrigin: String.Companion): retrofit2.Call<Origen>
}