package com.ruta.perubus

import android.content.Intent
import android.net.DnsResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.ruta.perubus.api.Api
import com.ruta.perubus.api.RetrofitClient
import com.ruta.perubus.models.User
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val context = this
        val login: Button = findViewById(R.id.LoginCreate)
        val nroCelular: EditText = findViewById(R.id.nroCelular)
        val correo: EditText = findViewById(R.id.correo)
        val nombre: EditText = findViewById(R.id.nombre)
        val apellido: EditText = findViewById(R.id.apellido)
        val pass: EditText = findViewById(R.id.Pass)
        val check: CheckBox = findViewById(R.id.checkBoxRegistro)
        val intent = Intent(this, RegistroActivity::class.java)

        login.setOnClickListener {
            val newUser = User()
            newUser.nroCelular = nroCelular.text.toString()
            newUser.correo = correo.text.toString()
            newUser.nombres = nombre.text.toString()
            newUser.apellidos = apellido.text.toString()
            newUser.contrasenia = pass.text.toString()
            newUser.origen = "app"

            val apiService = RetrofitClient.buildService(Api::class.java)
            val requestCall = apiService.createUser(newUser)

            requestCall.enqueue(object: retrofit2.Callback<User>{

                override fun onResponse(call: Call<User>, response: Response<User>){
                    if (response.isSuccessful){
                        finish()
                        check.isChecked
                        var newCreateUser = response.body()
                        Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }else{
                        Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
                }
            })

            }
    }
}