package com.ruta.perubus.ui.select.listener

import com.ruta.perubus.models.Bus

interface IBusItemListener {
    fun onBusItemClickListener(currentItem: Bus)
}