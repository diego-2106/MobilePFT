package com.example.pft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var editTextUser: EditText
    private lateinit var editTextPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextUser = findViewById(R.id.editTextUser)
        editTextPassword = findViewById(R.id.editTextPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener(View.OnClickListener {
            var username = editTextUser.text.toString()
            var password = editTextPassword.text.toString()
            try {

                if (username.isEmpty()) {
                    Toast.makeText(this, "El campo Usuario está vacío", Toast.LENGTH_SHORT).show()
                } else if (password.isEmpty()){
                    Toast.makeText(this, "El campo Contraseña está vacío", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, NavActivity::class.java)
                    startActivity(intent)
                }

            } catch (e:Exception) {
                Log.e("tag", "Se produjo un error: ${e.message}")
            }

        })

    }
}