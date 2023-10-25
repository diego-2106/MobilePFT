package com.example.pft

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class NavActivity : AppCompatActivity(){
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_activity)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)


        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.nav_home -> Toast.makeText(applicationContext, "Has clickeado Inicio", Toast.LENGTH_SHORT).show()
                R.id.mediciones -> Toast.makeText(applicationContext, "Has clickeado Mediciones", Toast.LENGTH_SHORT).show()
                R.id.manual -> Toast.makeText(applicationContext, "Has clickeado Manual", Toast.LENGTH_SHORT).show()
                R.id.settings -> Toast.makeText(applicationContext, "Has clickeado Ajustes", Toast.LENGTH_SHORT).show()
                R.id.version -> Toast.makeText(applicationContext, "Has clickeado Version", Toast.LENGTH_SHORT).show()

            }

            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true

        }
        return super.onOptionsItemSelected(item)
    }

}