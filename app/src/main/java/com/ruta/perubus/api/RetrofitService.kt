package com.ruta.perubus.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    var retrofitService: RetrofitClient? = null

    fun getInstance(): RetrofitClient {

        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://181.224.255.236:1001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(RetrofitClient::class.java)
        }
        return retrofitService!!
    }
}