package com.ruta.perubus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.ruta.perubus.api.RetrofitClient
import com.ruta.perubus.service.LoginResponse
import com.ruta.perubus.storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class ProviderType {
    Facebook
}

class CredentialActivity : AppCompatActivity() {

    private val callbackManager = CallbackManager.Factory.create()

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_credential)

        val Login: Button = findViewById(R.id.Login)
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val facebook: Button = findViewById(R.id.login_button)
        val textError = "Usuario no encontrado"
        val duration = Toast.LENGTH_SHORT

        Login.setOnClickListener(View.OnClickListener() {
            val email = username.text.toString().trim()
            val password = password.text.toString().trim()

            if(email.isEmpty()){
                username.error = "Email required"
                username.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){
                password.error = "Password required"
                password.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.userLogin(email, password)
                .enqueue(object: Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if(!response.body()?.error!!){

                            SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)

                            val intent = Intent(applicationContext, HomeActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            startActivity(intent)


                        }else{
                            Toast.makeText(applicationContext, textError, duration).show()
                        }

                    }
                })


        });

        facebook.setOnClickListener {

            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

                LoginManager.getInstance().registerCallback(callbackManager,
                object  : FacebookCallback<LoginResult>{

                    override fun onSuccess(result: LoginResult?) {
                        result?.let {
                            val token = it.accessToken

                            val credential = FacebookAuthProvider.getCredential(token.token)

                            FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                                if (it.isSuccessful){
                                    showHome(it.result?.user?.email ?: "", ProviderType.Facebook)
                                } else{
                                    showAlert()
                                }
                            }
                        }
                    }
                    override fun onCancel() {

                    }
                    override fun onError(error: FacebookException?) {
                            showAlert()
                    }
                })
        }

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}