package com.ruta.perubus.models

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("nu_tarifa")
    var nuTarifa: String,
    @SerializedName("codDestinoOrigen")
    var codDestOrigen: String,
    @SerializedName("codDestinoFinal")
    var codDestFinal: String,
    @SerializedName("precio")
    var precio: Int
)


