package com.ruta.perubus.ui.agencias

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruta.perubus.models.Agencia
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgenciasViewModel constructor(private val repository: AgenciasRepository) : ViewModel() {
    val agenciaList = MutableLiveData<List<Agencia>>()
    val errorMessage = MutableLiveData<String>()

    fun getAgencias() {
        val response = repository.getAgencias()
        response.enqueue(object : Callback<List<Agencia>> {
            override fun onResponse(call: Call<List<Agencia>>, response: Response<List<Agencia>>) {

                if (response.body()!!.isEmpty()) {
                    errorMessage.postValue("No se encontraron agencias.")
                } else {
                    agenciaList.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<List<Agencia>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}