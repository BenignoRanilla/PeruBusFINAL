package com.ruta.perubus.ui.agencias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AgenciasViewModelFactory constructor(private val repository: AgenciasRepository) :

    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AgenciasViewModel::class.java)) {
            AgenciasViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}