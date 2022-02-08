package com.ruta.perubus.storage

import android.content.Context
import com.ruta.perubus.models.User

class SharedPrefManager  private constructor(private val mCtx: Context){

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: User
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                sharedPreferences.getString("nroCelular", null),
                sharedPreferences.getString("contrasenia", null),
                sharedPreferences.getString("nombres", null),
                sharedPreferences.getString("apellidos", null),
                sharedPreferences.getString("correo", null),
                sharedPreferences.getString("origen", null)
            )
        }


    fun saveUser(user: User) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("nroCelular", user.nroCelular)
        editor.putString("contrasenia", user.contrasenia)
        editor.putString("nombres", user.nombres)
        editor.putString("apellidos", user.apellidos)
        editor.putString("correo", user.correo)
        editor.putString("origen", user.origen)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager

        }
    }
}