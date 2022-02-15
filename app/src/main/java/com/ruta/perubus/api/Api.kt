package com.ruta.perubus.api

import android.telecom.Call
import com.ruta.perubus.models.EditUser
import com.ruta.perubus.models.LoggedInUser
import com.ruta.perubus.models.ResetPass
import com.ruta.perubus.models.User
import retrofit2.http.*
import java.lang.StringBuilder

interface Api {

    @POST("Usuario/RegistrarUsuario")
    fun createUser(@Body newUser: User): retrofit2.Call<User>

    //@FormUrlEncoded
    @POST("/Usuario/IniciarSesion")
    fun userLogin(@Body newSession: LoggedInUser): retrofit2.Call<LoggedInUser>

    @PUT("Usuario/ModificarUsuario/{CoUsua}")
    fun resetPassword(@Body reset: ResetPass): retrofit2.Call<ResetPass>

    @PUT ("Usuario/ModificarUsuario/{CoUsua}")
    fun editProfile(@Body editUser: EditUser): retrofit2.Call<EditUser>
}