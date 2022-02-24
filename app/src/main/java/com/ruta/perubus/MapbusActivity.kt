package com.ruta.perubus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ruta.perubus.databinding.ActivityMapbusBinding
import com.ruta.perubus.models.Markers

class MapbusActivity : AppCompatActivity() {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapbusBinding
    lateinit var markers: Markers
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        markers = (intent.getSerializableExtra("markers") as? Markers)!!
        binding = ActivityMapbusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codbus : TextView = findViewById(R.id.codBus)
        val fechaProg : TextView = findViewById(R.id.fechaProg)
        val tipoServicio : TextView = findViewById(R.id.tipoServicio)
        val duracion : TextView = findViewById(R.id.duracion)
        val distancia : TextView = findViewById(R.id.distancia)

        codbus.text = markers.codbus
        fechaProg.text = markers.fechaProg
        tipoServicio.text = markers.tipoServicio
        duracion.text = markers.duracion
        distancia.text = markers.distancia

    }

}