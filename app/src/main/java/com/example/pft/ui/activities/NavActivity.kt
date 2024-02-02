package com.example.pft.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pft.ui.fragments.FragmentoMediciones
import com.example.pft.R
import com.example.pft.models.UsuarioDTO
import com.example.pft.ui.fragments.FragmentoAjustes
import com.example.pft.ui.fragments.FragmentoInicio
import com.example.pft.ui.fragments.FragmentoListaMediciones
import com.example.pft.ui.fragments.FragmentoVersion
import com.google.android.material.navigation.NavigationView

class NavActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_activity)

        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // Obtener el objeto UsuarioDTO del Intent
        val usuario = intent.getSerializableExtra("usuarios") as? UsuarioDTO

        // Verificar si el usuario no es nulo y actualizar los TextViews en el HeaderView
        if (usuario != null) {
            val navigationView: NavigationView = findViewById(R.id.nav_view)
            val headerView: View = navigationView.getHeaderView(0) // Obtén el primer HeaderView (puede haber más si usas múltiples menús deslizantes)

            // Actualizar el TextView del nombre del usuario (user_name)
            val userNameTextView: TextView = headerView.findViewById(R.id.user_name)
            userNameTextView.text = "Usuario: ${usuario.nombre} ${usuario.apellido}"

        }


        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(applicationContext, "Clickeaste Inicio", Toast.LENGTH_SHORT).show()
                    // Cargar un fragmento
                    val fragment = FragmentoInicio()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                }
                R.id.mediciones -> {
                    Toast.makeText(applicationContext, "Clickeaste Mediciones", Toast.LENGTH_SHORT).show()
                    // Cargar un fragmento
                    val fragment = FragmentoMediciones()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.lista_mediciones -> {
                    Toast.makeText(applicationContext, "Clickeaste Listado de Mediciones", Toast.LENGTH_SHORT).show()
                    // Cargar un fragmento
                    val fragment = FragmentoListaMediciones()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.manual -> {
                    Toast.makeText(applicationContext, "Clickeaste Manual", Toast.LENGTH_SHORT).show()
                    // Cargar un fragmento
                    val intent = Intent(this, AbrirManual::class.java)
                    startActivity(intent)
                }
                R.id.settings -> {
                    Toast.makeText(applicationContext, "Clickeaste Ajustes", Toast.LENGTH_SHORT).show()
                    val fragment = FragmentoAjustes()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()

                }
                R.id.version -> {
                    Toast.makeText(applicationContext, "Clickeaste Version", Toast.LENGTH_SHORT).show()
                    val fragment = FragmentoVersion()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.logout -> {
                    mostrarMensajeLogout()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun mostrarMensajeLogout() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cerrar Sesión")
        builder.setMessage("¿Estás seguro de que deseas cerrar sesión?")
        builder.setIcon(R.drawable.ic_baseline_warning_24)

        builder.setPositiveButton("Cerrar Sesión") { dialogInterface, i ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("Cancelar") { dialogInterface, i ->
            dialogInterface.dismiss()
        }

        builder.create().show()
    }
}