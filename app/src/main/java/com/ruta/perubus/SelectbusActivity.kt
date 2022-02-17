package com.ruta.perubus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ruta.perubus.databinding.ActivitySelectbusBinding
import com.ruta.perubus.models.Bus
import com.ruta.perubus.ui.MyAdapter

class SelectbusActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectbusBinding
    private lateinit var userArrayList : ArrayList<Bus>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectbusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codBus = arrayOf(
            "0630",
            "0590",
            "0490",
            "0290"
        )

        val fechaProg = arrayOf(
            "2020-02-07",
            "2020-02-08",
            "2020-02-08",
            "2020-03-08"
        )

        val map3 = arrayOf(
            "Santiago de Surco",
            "Surquillo",
            "La Victoria",
            "La Molina"
        )

        val tipoServicio = arrayOf(
            "SERV. ESTANDAR",
            "SERV. VIP",
            "SERV. ESTANDAR",
            "SERV. VIP"
        )

        val distancia = arrayOf(
            "280 km",
            "320 km",
            "330 km",
            "250 km"
        )

        val duracion = arrayOf(
            "3 hours 44 mins",
            "2 hours 15 mins",
            "1 hours 15 mins",
            "2 hours 35 mins"
        )

        userArrayList = ArrayList()

        for(i in codBus.indices){
            val bus = Bus(fechaProg[i], codBus[i], tipoServicio[i], map3[i], distancia[i], duracion[i])
            userArrayList.add(bus)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = MyAdapter(this, userArrayList)
        binding.listview.setOnItemClickListener{parent, view, position, id ->

            val codBus = codBus[position]
            val fechaProg = fechaProg[position]
            val map3 = map3[position]
            val tipoServicio = tipoServicio[position]
            val distancia = distancia[position]
            val duracion = duracion[position]

            val i = Intent(this, BuslocationActivity::class.java)
            i.putExtra("codBus", codBus)
            i.putExtra("fechProg", fechaProg)
            i.putExtra("map3", map3)
            i.putExtra("tipoServicio", tipoServicio)
            i.putExtra("distancia", distancia)
            i.putExtra("duracion", duracion)
            startActivity(i)
        }

    }
}