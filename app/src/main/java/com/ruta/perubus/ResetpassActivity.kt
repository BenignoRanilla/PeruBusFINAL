package com.ruta.perubus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ruta.perubus.api.Api
import com.ruta.perubus.api.RetrofitClient
import com.ruta.perubus.models.ResetPass
import retrofit2.Call
import retrofit2.Response

class ResetpassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpass)

        val context = this
        val resetpass: Button = findViewById(R.id.Login)
        val pass: EditText = findViewById(R.id.editText5)
        val intent = Intent(this,ResetpassActivity::class.java)

        resetpass.setOnClickListener {
            val reset = ResetPass()
            reset.contrasenia = pass.text.toString()

            val apiService = RetrofitClient.buildService(Api::class.java)
            val requestCall = apiService.resetPassword(reset)

            requestCall.enqueue(object: retrofit2.Callback<ResetPass>{

                override fun onResponse(call: Call<ResetPass>, response: Response<ResetPass>) {
                    if (response.isSuccessful){
                        finish()
                        Toast.makeText(context, "Successfully password changed", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(context, "Failed to change password", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResetPass>, t: Throwable) {
                    Toast.makeText(context,"Failed to change password", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}