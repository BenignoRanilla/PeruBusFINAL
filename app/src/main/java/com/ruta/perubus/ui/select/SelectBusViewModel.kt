package com.ruta.perubus.ui.select

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruta.perubus.models.Destino
import com.ruta.perubus.models.Itinerario
import com.ruta.perubus.models.ItinerarioInput
import com.ruta.perubus.models.Origen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SelectBusViewModel constructor(private val repository: SelectBusRepository) : ViewModel() {

    val originList = MutableLiveData<List<Origen>>()
    val destinyList = MutableLiveData<List<Destino>>()
    val itinerarioList = MutableLiveData<List<Itinerario>>()

    val errorMessage = MutableLiveData<String>()

    fun getAllOrigins() {
        val response = repository.getAllOrigins()
        response.enqueue(object : Callback<List<Origen>> {
            override fun onResponse(call: Call<List<Origen>>, response: Response<List<Origen>>) {
                originList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Origen>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getDestiny(origin: String) {
        val response = repository.getDestiniesByOrigin(origin)
        response.enqueue(object : Callback<List<Destino>> {
            override fun onResponse(call: Call<List<Destino>>, response: Response<List<Destino>>) {
                destinyList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Destino>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getItinerario(position: String, itinerario: ItinerarioInput) {
        //val response = repository.getItinerario(position, itinerario)
        val response = repository.getItinerario("-14.018002,-75.734469", itinerario)
        response.enqueue(object : Callback<List<Itinerario>> {
            override fun onResponse(
                call: Call<List<Itinerario>>,
                response: Response<List<Itinerario>>
            ) {
                itinerarioList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Itinerario>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}