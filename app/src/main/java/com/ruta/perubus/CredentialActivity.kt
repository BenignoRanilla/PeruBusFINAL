package com.ruta.perubus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CredentialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credential)

        val Login: Button = findViewById(R.id.Login)
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)

        Login.setOnClickListener {
            var status = if (username.text.toString().equals("Admin")
                && password.text.toString().equals("Password*123")) "Inicio de Sesión Exitoso"
            else "Inicio de Sesión Fallido"

            Toast.makeText( this,status, Toast.LENGTH_SHORT).show()

            setContentView(R.layout.activity_selectbus)
        }
    }
}