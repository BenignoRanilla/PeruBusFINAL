package com.ruta.perubus.ui.select

import com.ruta.perubus.api.Api
import com.ruta.perubus.models.ItinerarioInput

class SelectBusRepository constructor(private val api: Api) {
    fun getAllOrigins() = api.ObtenerOrigen()

    fun getDestiniesByOrigin(origin: String) = api.obtenerDestino(origin)

    fun getItinerario(position: String, itinerario: ItinerarioInput) =
        api.ObtenerItinerario(position, itinerario)

    fun getTarifaRuta(
        codRumbo: String,
        fechProg: String,
        numSec: String,
        coEmp: String,
        codOrigen: String,
        codFinal: String
    ) = api.obtenerTarifaRuta(
        codRumbo,
        fechProg,
        numSec,
        coEmp,
        codOrigen,
        codFinal
    )

}