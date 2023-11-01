package com.example.pft.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.pft.R
import com.example.pft.data.RepositorioMediciones
import com.example.pft.models.Medicion

class FragmentoEntradaMediciones : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmento_entrada_mediciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
        val medicion1EditText: EditText = view.findViewById(R.id.medicion1EditText)
        val medicion2EditText: EditText = view.findViewById(R.id.medicion2EditText)
        val saveButton: Button = view.findViewById(R.id.saveButton)

        // Si recibiste una medicion como argumento, puedes mostrarla aquí.
        // Asegúrate de que Medicion implemente Parcelable.
        val medicion: Medicion? = arguments?.getParcelable<Medicion>("medicion")
        if (medicion != null) {
            medicion1EditText.setText(medicion.medicion1)
            medicion2EditText.setText(medicion.medicion2)
        }


        saveButton.setOnClickListener {
            guardarMedicion(medicion1EditText, medicion2EditText)
        }
    }

    private fun guardarMedicion(medicion1EditText: EditText, medicion2EditText: EditText) {
        val medicion1 = medicion1EditText.text.toString()
        val medicion2 = medicion2EditText.text.toString()
        if (medicion1.isNotEmpty() && medicion2.isNotEmpty()) {
            RepositorioMediciones.agregarMedicion(medicion1, medicion2)
            // Opcional: Navegar de regreso al FragmentoListaMediciones o actualizar la lista directamente si están en el mismo fragmento.
        }*/
    }
}
