package com.ruta.perubus.api

import com.ruta.perubus.models.*
import retrofit2.http.*

interface Api {

    @POST("Usuario/RegistrarUsuario")
    fun createUser(@Body newUser: User): retrofit2.Call<User>

    @POST("/Usuario/IniciarSesion")
    fun userLogin(@Body newSession: LoggedInUser): retrofit2.Call<LoggedInUser>

    @PUT("Usuario/ModificarUsuario/{CoUsua}")
    fun resetPassword(@Body reset: ResetPass): retrofit2.Call<ResetPass>

    @PUT("Usuario/ModificarUsuario/{CoUsua}")
    fun editProfile(@Body editUser: EditUser): retrofit2.Call<EditUser>

    @POST("/TdprogItin/ObtenerItinerario/{position}")
    fun ObtenerItinerario(@Path("position") position: String, @Body itinerario: ItinerarioInput): retrofit2.Call<List<Itinerario>>

    @GET("/Tramos/ObtenerOrigen")
    fun ObtenerOrigen(): retrofit2.Call<List<Origen>>

    @GET("/Tramos/ObtenerDestinoRumbo/{origin}")
    fun obtenerDestino(@Path("origin") origin: String): retrofit2.Call<List<Destino>>
}