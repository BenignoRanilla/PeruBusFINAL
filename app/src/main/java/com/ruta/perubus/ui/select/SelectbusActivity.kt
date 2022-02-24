package com.ruta.perubus.ui.select

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ruta.perubus.BuslocationActivity
import com.ruta.perubus.R
import com.ruta.perubus.api.Api
import com.ruta.perubus.api.RetrofitClient
import com.ruta.perubus.databinding.ActivitySelectbusBinding
import com.ruta.perubus.models.Bus
import com.ruta.perubus.models.Origen
import com.ruta.perubus.ui.MyAdapter
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class SelectbusActivity : AppCompatActivity() {

    private val URL_ORIGEN_SPINNER =
        "http://181.224.255.236:1001/Tramos/ObtenerOrigen"

    private lateinit var binding: ActivitySelectbusBinding
    private lateinit var userArrayList: ArrayList<Bus>
    private lateinit var retrofit: Retrofit
    private lateinit var service: Api
    var origenArrayList: ArrayList<Origen> = arrayListOf()
    lateinit var opcionesOrigen: Spinner
    lateinit var opcionesDestino: Spinner
    lateinit var origenTxt: TextView
    lateinit var Destinotxt: TextView

    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectbusBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val apiService = RetrofitClient.buildService(Api::class.java)
        val requestCall = apiService.ObtenerOrigen()

        opcionesOrigen = findViewById(R.id.SpinnerOrigen)
        opcionesDestino = findViewById(R.id.SpinnerDestino)
        origenTxt = findViewById(R.id.origenText)

        val lista = listOf("Ica", "Lima", "Chincha", "Pisco", "Cañete")


        var originList: List<Origen>


        requestCall.enqueue(object : retrofit2.Callback<List<Origen>> {

            override fun onResponse(call: Call<List<Origen>>, response: Response<List<Origen>>) {
                if (response.isSuccessful) {
                    try {
                        originList = response.body()!!

                    } catch (e: Exception) {
                        Log.d("login", e.toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<Origen>>, t: Throwable) {
                Log.d("login", t.toString())
            }
        })

        service = createApiService()
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        opcionesOrigen.adapter = adaptador
        opcionesOrigen.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                origenTxt.setText(opcionesOrigen.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


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

        for (i in codBus.indices) {
            val bus =
                Bus(fechaProg[i], codBus[i], tipoServicio[i], map3[i], distancia[i], duracion[i])
            userArrayList.add(bus)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = MyAdapter(this, userArrayList)
        binding.listview.setOnItemClickListener { parent, view, position, id ->

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

    private fun createApiService(): Api {
        retrofit = Retrofit.Builder()
            .baseUrl("http://181.224.255.236:1001/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        return retrofit.create(Api::class.java)
    }


}

