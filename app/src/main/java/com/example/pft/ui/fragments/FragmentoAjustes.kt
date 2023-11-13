package com.example.pft.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.example.pft.R

class FragmentoAjustes : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragmento_ajustes, container, false)

        val switchModoOscuro = view.findViewById<Switch>(R.id.switchModoOscuro)

        // Cargar la preferencia guardada
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return view
        val modoOscuroActivado = sharedPref.getBoolean(getString(R.string.pref_modo_oscuro), false)

        switchModoOscuro.isChecked = modoOscuroActivado

        switchModoOscuro.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPref.edit().putBoolean(getString(R.string.pref_modo_oscuro), true).apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPref.edit().putBoolean(getString(R.string.pref_modo_oscuro), false).apply()
            }
        }

        return view
    }
}