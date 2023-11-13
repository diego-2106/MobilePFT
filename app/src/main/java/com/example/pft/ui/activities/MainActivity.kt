package com.example.pft.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pft.R

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
                else -> startNavActivity()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun startNavActivity() {
        startActivity(Intent(this, NavActivity::class.java))
    }
}
