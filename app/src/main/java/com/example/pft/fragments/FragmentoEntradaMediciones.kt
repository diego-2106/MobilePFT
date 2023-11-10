package com.example.pft.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.data.RepositorioMediciones
import com.example.pft.models.Medicion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentoEntradaMediciones : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmento_entrada_mediciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.fragmentoEntradaMediciones)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val medicion1EditText: EditText = view.findViewById(R.id.medicion1EditText)
        val medicion2EditText: EditText = view.findViewById(R.id.medicion2EditText)
        val medicion3EditText: EditText = view.findViewById(R.id.medicion3EditText)
        val medicion4EditText: EditText = view.findViewById(R.id.medicion4EditText)
        val saveButton: Button = view.findViewById(R.id.saveButton)

        val medicion: Medicion? = arguments?.getParcelable<Medicion>("medicion")
        if (medicion != null) {
            medicion1EditText.setText(medicion.medicion1)
            medicion2EditText.setText(medicion.medicion2)
            medicion3EditText.setText(medicion.medicion3)
            medicion4EditText.setText(medicion.medicion4)
        }
        saveButton.setOnClickListener {
            guardarMedicion(medicion1EditText, medicion2EditText, medicion3EditText, medicion4EditText )
        }
    }

    fun guardarMedicion(medicion1EditText: EditText, medicion2EditText: EditText, medicion3EditText: EditText, medicion4EditText: EditText) {
        val medicion1 = medicion1EditText.text.toString()
        val medicion2 = medicion2EditText.text.toString()
        val medicion3 = medicion3EditText.text.toString()
        val medicion4 = medicion4EditText.text.toString()
        if (medicion1.isNotEmpty() && medicion2.isNotEmpty()) {
            // Inicia una coroutine en el contexto de la UI
            lifecycleScope.launch {
                // Cambia al contexto de ejecución de E/S para la operación de guardado
                withContext(Dispatchers.IO) {
                    val id = RepositorioMediciones.generarId()
                    val nuevaMedicion = Medicion(id, medicion1, medicion2, medicion3, medicion4)
                    RepositorioMediciones.agregarMedicion(nuevaMedicion)
                }
                // Una vez completada la operación de E/S, se retoma la coroutine en el hilo principal
                // Ahora se muestra un mensaje de confirmación y navegar al otro fragmento
                Toast.makeText(requireContext(), "Medición guardada", Toast.LENGTH_SHORT).show()
                navegarAlListado()
            }
        }
    }

    private fun navegarAlListado() {
        findNavController().navigate(R.id.action_fragmentoEntradaMediciones_to_fragmentoListaMediciones)
    }
}
