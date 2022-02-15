package com.ruta.perubus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RecoverypassActivity : AppCompatActivity() {

    val passEditText = findViewById<EditText>(R.id.editText2)
    val recoveryButton = findViewById<Button>(R.id.Validar)
    private var recovery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recoverypass)

        val passEditText = findViewById<EditText>(R.id.editText2)

        recoveryButton.setOnClickListener{
            recovery = passEditText.text.toString().trim()

            if (recovery.isNotEmpty()) {
                resetPassword(recovery)
                Toast.makeText(this@RecoverypassActivity, "Contraseña vacía", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@RecoverypassActivity, "Email vacio", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun resetPassword(recovery: String){

    }
}