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
    fun ObtenerItinerario(
        @Path("position") position: String,
        @Body itinerario: ItinerarioInput
    ): retrofit2.Call<List<Itinerario>>

    @GET("/Tramos/ObtenerOrigen")
    fun ObtenerOrigen(): retrofit2.Call<List<Origen>>

    @GET("/Tramos/ObtenerDestinoRumbo/{origin}")
    fun obtenerDestino(@Path("origin") origin: String): retrofit2.Call<List<Destino>>

    @GET("/TarifaRuta/ObtenerTarifaRuta/{pCO_RUMB}/{pFE_PROG}/{pNU_SECU}/{pCO_EMPR}/{pCO_DEST_ORIG}/{pCO_DEST_FINA}")
    fun obtenerTarifaRuta(
        @Path("pCO_RUMB") codRumbo: String,
        @Path("pFE_PROG") fechProg: String,
        @Path("pNU_SECU") numSec: String,
        @Path("pCO_EMPR") coEmp: String,
        @Path("pCO_DEST_ORIG") codOrigen: String,
        @Path("pCO_DEST_FINA") codFinal: String
    ): retrofit2.Call<List<Price>>

    @GET("/Agencias/ObtenerGPSAgencias")
    fun obtenerAgencias(): retrofit2.Call<List<Agencia>>


}