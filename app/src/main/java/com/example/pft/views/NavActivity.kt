package com.example.pft.views

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pft.fragments.FragmentoMediciones
import com.example.pft.R
import com.google.android.material.navigation.NavigationView

class NavActivity : AppCompatActivity(){
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_activity)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)


        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle) /*le defino al toggle como listener para que cambie de icono dependiendo si abre o no la nav*/
        toggle.syncState()

        /*Lo que se hace aca es habilitar la flecha para ir hacia atras en caso de que este abierto el menu*/
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(applicationContext, "Clickeaste Inicio", Toast.LENGTH_SHORT).show()
                    // cargar un fragmento
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
                    // cargar un fragmento
                }
                R.id.settings -> {
                    Toast.makeText(applicationContext, "Clickeaste Ajustes", Toast.LENGTH_SHORT).show()
                    // cargar un fragmento
                }
                R.id.version -> {
                    Toast.makeText(applicationContext, "Clickeaste Version", Toast.LENGTH_SHORT).show()
                    // cargar un fragmento
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)  // Cierra el drawer
            true  // selección del ítem
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true

        }
        return super.onOptionsItemSelected(item)
    }

}