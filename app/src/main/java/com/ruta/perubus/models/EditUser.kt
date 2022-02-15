package com.ruta.perubus.models

data class EditUser(
    var nroCelular:String? = null,
    var contrasenia:String? = null,
    var nombres:String? = null,
    var apellidos:String? = null,
    var correo:String? = null,
    var contraseniarepeat:String? = null
)