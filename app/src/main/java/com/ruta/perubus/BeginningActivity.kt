package com.ruta.perubus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BeginningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beginning)

        val button: Button = findViewById(R.id.Ingresar)
        button.setOnClickListener {

            setContentView(R.layout.activity_credential)
        }

    }
}