package com.example.pft.views

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pft.fragments.FragmentoMediciones
import com.example.pft.R
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

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(applicationContext, "Clickeaste Inicio", Toast.LENGTH_SHORT).show()
                    // Cargar un fragmento o realizar otras acciones relacionadas con "Inicio"
                }
                R.id.mediciones -> {
                    val fragment = FragmentoMediciones()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.manual -> {
                    Toast.makeText(applicationContext, "Clickeaste Manual", Toast.LENGTH_SHORT).show()
                    // Cargar un fragmento o realizar otras acciones relacionadas con "Manual"
                }
                R.id.settings -> {
                    Toast.makeText(applicationContext, "Clickeaste Ajustes", Toast.LENGTH_SHORT).show()
                    // Cargar un fragmento o realizar otras acciones relacionadas con "Ajustes"
                }
                R.id.version -> {
                    Toast.makeText(applicationContext, "Clickeaste Version", Toast.LENGTH_SHORT).show()
                    // Cargar un fragmento o realizar otras acciones relacionadas con "Versión"
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