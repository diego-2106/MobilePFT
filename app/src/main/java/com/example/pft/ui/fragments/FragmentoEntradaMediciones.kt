package com.example.pft.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pft.R
import com.example.pft.models.Medicion
import com.example.pft.viewmodel.MedicionViewModel

class FragmentoEntradaMediciones : Fragment() {

    // Variables para los campos de texto y botones
    private lateinit var viewModel: MedicionViewModel
    private lateinit var medicion1EditText: EditText
    private lateinit var medicion2EditText: EditText
    private lateinit var medicion3EditText: EditText
    private lateinit var medicion4EditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate del layout para este fragmento
        return inflater.inflate(R.layout.fragmento_entrada_mediciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización del ViewModel
        viewModel = ViewModelProvider(this).get(MedicionViewModel::class.java)

        // Inicialización de los campos de texto
        medicion1EditText = view.findViewById(R.id.medicion1EditText)
        medicion2EditText = view.findViewById(R.id.medicion2EditText)
        medicion3EditText = view.findViewById(R.id.medicion3EditText)
        medicion4EditText = view.findViewById(R.id.medicion4EditText)

        // Configuración del botón de guardar
        val saveButton: Button = view.findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            guardarMedicion()
        }
    }

    private fun guardarMedicion() {
        if (validarCampos()) {
            // Crear el objeto Medicion con los datos recogidos
            val nuevaMedicion = Medicion(
                viewModel.generarId(), // Asumiendo que generarId es un método en tu ViewModel
                medicion1EditText.text.toString(),
                medicion2EditText.text.toString(),
                medicion3EditText.text.toString(),
                medicion4EditText.text.toString()
            )

            // Usar el ViewModel para guardar la medición
            viewModel.agregarMedicion(nuevaMedicion)

            // Confirmar al usuario y navegar
            Toast.makeText(requireContext(), "Medición guardada", Toast.LENGTH_SHORT).show()
            navegarAlListado()
        } else {
            mostrarMensajeError()
        }
    }

    private fun validarCampos(): Boolean {
        // Validar que los campos no estén vacíos
        return medicion1EditText.text.isNotEmpty() &&
                medicion2EditText.text.isNotEmpty() &&
                medicion3EditText.text.isNotEmpty() &&
                medicion4EditText.text.isNotEmpty()
    }

    private fun mostrarMensajeError() {
        // Mostrar un mensaje de error si la validación falla
        Toast.makeText(requireContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show()
    }

    private fun navegarAlListado() {
        // Navegar al listado de mediciones
        findNavController().navigate(R.id.action_fragmentoEntradaMediciones_to_fragmentoListaMediciones)
    }
}
