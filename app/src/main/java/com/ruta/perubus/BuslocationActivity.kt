package com.ruta.perubus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ruta.perubus.databinding.ActivityBuslocationBinding

class BuslocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBuslocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuslocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codBus = intent.getStringExtra("codBus")
        val fechProg = intent.getStringExtra("fechProg")
        val map3 = intent.getStringExtra("map3")
        val tipoServicio = intent.getStringExtra("tipoServicio")
        val distancia = intent.getStringExtra("distancia")
        val duracion = intent.getStringExtra("duracion")

        binding.codBus.text = codBus
        binding.fechaProg.text = fechProg
        binding.map3.text = map3
        binding.tipoServicio.text = tipoServicio
        binding.distancia.text = distancia
        binding.duracion.text = duracion



    }
}