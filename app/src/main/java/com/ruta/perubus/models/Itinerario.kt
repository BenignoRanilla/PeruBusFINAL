package com.ruta.perubus.models

import com.google.gson.annotations.SerializedName


data class Itinerario(
    @SerializedName("fechaProg") var fechaProg: String,
    @SerializedName("codBus") var codBus: String,
    @SerializedName("tipoServicio") var tipoServicio: String,
    @SerializedName("map3") var map3: String,
    @SerializedName("distancia") var distancia: String,
    @SerializedName("duracion") var duracion: String,
    @SerializedName("latitude") var latitude: String,
    @SerializedName("longitude") var longitude: String
)


