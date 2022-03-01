package com.ruta.perubus.ui.agencias

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.ruta.perubus.R
import com.ruta.perubus.api.Api
import com.ruta.perubus.api.RetrofitClient
import com.ruta.perubus.databinding.ActivityAgenciasBinding
import com.ruta.perubus.models.Markers

class AgenciasActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityAgenciasBinding
    lateinit var viewModel: AgenciasViewModel
    lateinit var markers: Markers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        markers = (intent.getSerializableExtra("markers") as? Markers)!!

        binding = ActivityAgenciasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val api = RetrofitClient.buildService(Api::class.java)

        viewModel =
            ViewModelProvider(this, AgenciasViewModelFactory(AgenciasRepository(api))).get(
                AgenciasViewModel::class.java
            )

        viewModel.agenciaList.observe(this, Observer {
            //val builder = LatLngBounds.Builder()
            for (agencia in it) {

                val marker = LatLng(
                    agencia.latitud.toDouble(),
                    agencia.longitud.toDouble()
                )

                mMap.addMarker(
                    MarkerOptions()
                        .position(
                            marker
                        )
                        .title(agencia.nombreAgencia)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus_bitmap))
                )

                /*
                builder.include(marker)
                mMap.setOnMapLoadedCallback {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100))
                }

                 */
                mMap.uiSettings.isMapToolbarEnabled = false;
            }


        })
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(
                applicationContext,
                it,
                Toast.LENGTH_SHORT
            ).show()
        })

        binding.fabItinerario.setOnClickListener {
            finish()
        }


    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val user = LatLng(
            markers.currentLatitude.toDouble(),
            markers.currentLongitude.toDouble()
        )
/*
        mMap.addMarker(
            MarkerOptions()
                .position(user)
                .title("Usuario")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.user))
        )?.showInfoWindow()

 */
        mMap.addMarker(
            MarkerOptions()
                .position(user)
                .title("Usuario")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.user))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user,11.0f))

        viewModel.getAgencias()
    }

}