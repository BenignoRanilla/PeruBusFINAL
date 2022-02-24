package com.ruta.perubus.models

import java.io.Serializable

data class Markers(
    var currentLatitude: String,
    var currentLongitude: String,
    var busLatitude: String,
    var busLongitude: String,
    var codbus: String,
    var fechaProg: String,
    var duracion : String,
    var tipoServicio : String,
    var distancia: String
) : Serializable