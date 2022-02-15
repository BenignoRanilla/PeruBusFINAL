package com.ruta.perubus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ruta.perubus.api.Api
import com.ruta.perubus.api.RetrofitClient
import com.ruta.perubus.models.EditUser
import retrofit2.Call
import retrofit2.Response

class EditarperfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editarperfil)

        val context = this
        val guardar: Button = findViewById(R.id.Guardar)
        val nroCelular: EditText = findViewById(R.id.nroCelular)
        val correo: EditText = findViewById(R.id.correo)
        val nombre: EditText = findViewById(R.id.nombre)
        val apellido: EditText = findViewById(R.id.apellido)
        val pass: EditText = findViewById(R.id.Pass)
        val passRepeat: EditText = findViewById(R.id.passRepeat)
        val intent = Intent(this,EditarperfilActivity::class.java)

        guardar.setOnClickListener{
            val editUser = EditUser()
            editUser.nroCelular = nroCelular.text.toString()
            editUser.correo = correo.text.toString()
            editUser.nombres = nombre.text.toString()
            editUser.apellidos = apellido.text.toString()
            editUser.contrasenia = pass.text.toString()
            editUser.contraseniarepeat = passRepeat.text.toString()

            val apiService = RetrofitClient.buildService(Api::class.java)
            val requestCall =apiService.editProfile(editUser)

            requestCall.enqueue(object: retrofit2.Callback<EditUser>{

                override fun onResponse(call: Call<EditUser>, response: Response<EditUser>) {
                    if (response.isSuccessful){
                        finish()
                        Toast.makeText(context,"Successfully update",Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(context,"Failed to update",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<EditUser>, t: Throwable) {
                    Toast.makeText(context,"Failed to update",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}