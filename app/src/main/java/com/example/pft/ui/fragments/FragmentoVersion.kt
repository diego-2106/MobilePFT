package com.example.pft.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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

        // Configurar el ImageBtn para abrir la configuración de aplicaciones
        val buttonOpenAppSettings = view.findViewById<ImageButton>(R.id.button_info_app)
        buttonOpenAppSettings.setOnClickListener {
            val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", requireActivity().packageName, null)
            intent.data = uri
            startActivity(intent)
        }

        return view
    }
}
