package com.ruta.perubus.models

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    var NroCelular: String? = null,
    var Contrasenia: String? = null
)