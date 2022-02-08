package com.ruta.perubus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class BeginningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beginning)

        val ingresar: Button = findViewById(R.id.registro)
        val button: Button = findViewById(R.id.Ingresar)
        button.setOnClickListener {

            setContentView(R.layout.activity_credential)
        }

    }

    fun getRegistro(view: View){
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
        finish()
    }
}