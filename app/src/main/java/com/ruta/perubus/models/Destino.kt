package com.ruta.perubus.models

import com.google.gson.annotations.SerializedName

data class Destino(

    @SerializedName("codRumbo") var codRumbo: String,
    @SerializedName("codigoDestino") var codigoDestino: String,
    @SerializedName("descripcionOrigen") var descripcionOrigen: String
) {

    override fun toString(): String {
        return descripcionOrigen
    }

}