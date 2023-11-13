package com.example.pft.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pft.R
import de.hdodenhof.circleimageview.BuildConfig

class FragmentoVersion : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmento_version, container, false)

        // Configurar la vista para mostrar la versión actual
        val textViewVersion = view.findViewById<TextView>(R.id.textViewVersion)
        textViewVersion.text = getString(R.string.version_format, BuildConfig.VERSION_NAME)

        return view
    }
}
