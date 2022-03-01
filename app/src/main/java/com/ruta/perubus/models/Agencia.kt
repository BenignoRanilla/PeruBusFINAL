package com.ruta.perubus.models

import com.google.gson.annotations.SerializedName

data class Agencia (
    @SerializedName("nombreAgencia") var nombreAgencia: String,
    @SerializedName("direccionAgencia") var direccionAgencia: String,
    @SerializedName("latitud") var latitud: String,
    @SerializedName("longitud") var longitud: String
)
