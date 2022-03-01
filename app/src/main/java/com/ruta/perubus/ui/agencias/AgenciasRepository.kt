package com.ruta.perubus.ui.agencias

import com.ruta.perubus.api.Api

class AgenciasRepository constructor(private val api: Api) {
    fun getAgencias() = api.obtenerAgencias()
}