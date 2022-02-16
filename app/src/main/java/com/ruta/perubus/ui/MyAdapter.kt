package com.ruta.perubus.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ruta.perubus.R
import com.ruta.perubus.models.Bus
import org.w3c.dom.Text

class MyAdapter(private val context: Activity, private val arraylist: ArrayList<Bus>): ArrayAdapter<Bus>(context,
                R.layout.list_item, arraylist){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null)

        val codBus: TextView = view.findViewById(R.id.codBus)
        val fechaProg : TextView = view.findViewById(R.id.fechaProg)
        val tipoServicio : TextView = view.findViewById(R.id.tipoServicio)
        val map3 : TextView = view.findViewById(R.id.map3)
        val distancia : TextView = view.findViewById(R.id.distancia)
        val duracion : TextView = view.findViewById(R.id.duracion)

        codBus.text = arraylist[position].codBus
        fechaProg.text = arraylist[position].fechaProg
        tipoServicio.text = arraylist[position].tipoServicio
        map3.text = arraylist[position].map3
        distancia.text = arraylist[position].distancia
        duracion.text = arraylist[position].duracion

        return view
    }

}