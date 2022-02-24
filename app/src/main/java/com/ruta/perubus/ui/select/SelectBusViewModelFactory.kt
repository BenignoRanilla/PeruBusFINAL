package com.ruta.perubus.ui.select

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SelectBusViewModelFactory constructor(private val repository: SelectBusRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SelectBusViewModel::class.java)) {
            SelectBusViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}