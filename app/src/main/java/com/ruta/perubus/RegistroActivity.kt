package com.ruta.perubus

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ruta.perubus.api.Api
import com.ruta.perubus.api.RetrofitClient
import com.ruta.perubus.databinding.ActivityRegistroBinding
import com.ruta.perubus.helper.Utils
import com.ruta.perubus.models.User
import com.ruta.perubus.ui.select.SelectbusActivity
import retrofit2.Call
import retrofit2.Response
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoginCreate.setOnClickListener {
            val newUser = User()
            newUser.nroCelular = binding.nroCelular.text.toString()
            newUser.correo = binding.correo.text.toString()
            newUser.nombres = binding.nombre.text.toString()
            newUser.apellidos = binding.apellido.text.toString()
            newUser.contrasenia = binding.Pass.text.toString()
            newUser.origen = "app"

            val apiService = RetrofitClient.buildService(Api::class.java)
            val requestCall = apiService.createUser(newUser)

            requestCall.enqueue(object : retrofit2.Callback<User> {

                override fun onResponse(call: Call<User>, response: Response<User>) {

                    if (validarCampos()== true){
                        if (response.isSuccessful) {
                            finish()
    //                        check.isChecked
                            var newCreateUser = response.body()
                                Toast.makeText(applicationContext, "Usuario agregado.", Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(applicationContext, CredentialActivity::class.java)
                                startActivity(intent)
                            }else {
                                Toast.makeText(applicationContext, "Datos Incorrectos", Toast.LENGTH_SHORT)
                                    .show()
                                return
                            }

                    } else {
                        Toast.makeText(applicationContext, "Datos Incorrectos", Toast.LENGTH_SHORT)
                            .show()

                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(applicationContext, "Dato incorrecto", Toast.LENGTH_SHORT)
                        .show()
                }
            })

        }
    }

    private fun validarCampos(): Boolean {
        var datosCorrectos = true
        val context = this

        if (binding.nroCelular.text.toString().isEmpty() && binding.nroCelular.text.toString() <= "900000000") {
            Toast.makeText(context, "Debe de ingresar un número de celular", Toast.LENGTH_SHORT)
                .show()
            datosCorrectos = false;
        }
        else if (binding.correo.text.toString().isEmpty() && !isValidEmailAddress(binding.correo.text.toString())) {
            Toast.makeText(context, "Debe de ingresar un correo.", Toast.LENGTH_SHORT).show()
            datosCorrectos = false;
        } else if (binding.nombre.text.toString().isEmpty()) {
            Toast.makeText(context, "Debe de ingresar un nombre.", Toast.LENGTH_SHORT).show()
            datosCorrectos = false;
        } else if (binding.apellido.text.toString().isEmpty()) {
            Toast.makeText(context, "Debe de ingresar un contraseña.", Toast.LENGTH_SHORT).show()
            datosCorrectos = false;
        } else if (!binding.Pass.text.toString().isEmpty() || !binding.passRepeat.text.toString()
                .isEmpty()
        ) {
            if (!binding.Pass.text.toString().equals(binding.passRepeat.text.toString())) {
                Toast.makeText(context, "Las contraseñas deben de coincidir.", Toast.LENGTH_SHORT)
                    .show()
                datosCorrectos = false;
            }
        }

        return datosCorrectos
    }

   private fun isValidEmailAddress(emailAddress: String?): Boolean {
        val emailRegEx: String
        val pattern: Pattern
        // Regex for a valid email address
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$"
        // Compare the regex with the email address
        pattern = Pattern.compile(emailRegEx)
        val matcher: Matcher = pattern.matcher(emailAddress)
        return if (!matcher.find()) {
            false
        } else true
    }
}