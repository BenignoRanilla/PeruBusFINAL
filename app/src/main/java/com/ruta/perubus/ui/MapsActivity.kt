package com.ruta.perubus.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.PolyUtil
import com.ruta.perubus.R
import com.ruta.perubus.databinding.ActivityMapsBinding
import com.ruta.perubus.models.Markers
import org.json.JSONObject


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var markers: Markers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        markers = (intent.getSerializableExtra("markers") as? Markers)!!

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        val codbus: TextView = findViewById(R.id.codBusValue)
        val fechaProg: TextView = findViewById(R.id.fechaProgValue)
        val tipoServicio: TextView = findViewById(R.id.tipoServicioValue)
        val duracion: TextView = findViewById(R.id.duracionValue)
        val distancia: TextView = findViewById(R.id.distanciaValue)

        codbus.text = markers.codbus
        fechaProg.text = markers.fechaProg
        tipoServicio.text = markers.tipoServicio
        duracion.text = markers.duracion
        distancia.text = markers.distancia    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val user = LatLng(
            markers.currentLatitude.toDouble(),
            markers.currentLongitude.toDouble()
        )

        val bus = LatLng(
            markers.busLatitude.toDouble(),
            markers.busLongitude.toDouble()
        )

        val builder = LatLngBounds.Builder()
        builder.include(bus)
        builder.include(user)

        mMap.addMarker(
            MarkerOptions()
                .position(bus)
                .title("Bus")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus_bitmap))

        )
        mMap.addMarker(
            MarkerOptions()
                .position(user)
                .title("Usuario")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.user))
        )

        mMap.setOnMapLoadedCallback {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100))
        }
        mMap.uiSettings.isMapToolbarEnabled = false;

        val path: MutableList<List<LatLng>> = ArrayList()
        val urlDirections =
            "https://maps.googleapis.com/maps/api/directions/json?origin=" + user.latitude + "," + user.longitude + "&destination=" + bus.latitude + "," + bus.longitude + "&key=AIzaSyANUI7icvsdQheil0YvU0vhkvEMxHrNz2w"
        val directionsRequest = object :
            StringRequest(Request.Method.GET, urlDirections, Response.Listener<String> { response ->
                val jsonResponse = JSONObject(response)
                // Get routes
                val routes = jsonResponse.getJSONArray("routes")
                val legs = routes.getJSONObject(0).getJSONArray("legs")
                val steps = legs.getJSONObject(0).getJSONArray("steps")
                for (i in 0 until steps.length()) {
                    val points =
                        steps.getJSONObject(i).getJSONObject("polyline").getString("points")
                    path.add(PolyUtil.decode(points))
                }
                for (i in 0 until path.size) {
                    this.mMap!!.addPolyline(PolylineOptions().addAll(path[i]).color(Color.RED))
                }
            }, Response.ErrorListener { _ ->
            }) {}
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(directionsRequest)

    }

}