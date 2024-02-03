package com.example.pft.ui.activities

import android.content.Intent
import android.util.Log
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pft.R
import com.example.pft.models.LoginBody
import com.example.pft.models.UsuarioDTO
import com.example.pft.rest.RestAPI_Client
import com.example.pft.rest.RestAPI_Interface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var editTextUser: EditText
    private lateinit var editTextPassword: EditText

    companion object {
        const val TAG = "MainActivity"
        const val ERROR_FILL_FIELDS = "Por favor rellena los campos"
        const val ERROR_PASSWORD_REQUIRED = "El campo Contraseña es requerido"
        const val ERROR_USERNAME_REQUIRED = "El campo Usuario es requerido"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextUser = findViewById(R.id.editTextUser)
        editTextPassword = findViewById(R.id.editTextPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = editTextUser.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            when {
                username.isEmpty() && password.isEmpty() -> showToast(ERROR_FILL_FIELDS)
                username.isEmpty() -> showToast(ERROR_USERNAME_REQUIRED)
                password.isEmpty() -> showToast(ERROR_PASSWORD_REQUIRED)
                else -> doLogin(username, password)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun doLogin(username: String, password: String) {
        val loginRequest = LoginBody(username, password)

        val call = RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java).login(loginRequest)
        call.enqueue(object : Callback<UsuarioDTO?> {
            override fun onResponse(call: Call<UsuarioDTO?>, response: Response<UsuarioDTO?>) {
                if (response.isSuccessful) {
                    val usuarioLogin = response.body()
                    showToast("Login exitoso")
                    finish()
                    startActivity(Intent(this@MainActivity, NavActivity::class.java)
                        .putExtra("usuarios", usuarioLogin))
                } else {
                    showToast("Error en el login. Código: ${response.code()}")
                    showToast("Usuario o Contraseña incorrectos")
                }
            }

            override fun onFailure(call: Call<UsuarioDTO?>, t: Throwable) {
                showToast("Error en la llamada a la API: ${t.message}")
                Log.e(TAG, "Error en la llamada a la API", t)
            }
        })
    }
}