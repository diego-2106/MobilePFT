package com.example.pft.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.pft.R
import com.example.pft.models.Medicion

class DetalleMedicionDialogFragment : DialogFragment() {
    companion object {
        private const val ARG_MEDICION = "medicion"

        fun newInstance(medicion: Medicion): DetalleMedicionDialogFragment {
            val fragment = DetalleMedicionDialogFragment()
            val args = Bundle()
            args.putParcelable(ARG_MEDICION, medicion)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val medicion = arguments?.getParcelable<Medicion>(ARG_MEDICION)

        val mensaje = medicion?.let {
            "Detalles de la medición:\n" +
                    "Usuario Creador: ${it.medicion1}\n" +
                    "Fecha: ${it.medicion2}\n" +
                    "Formulario: ${it.medicion3}\n" +
                    "Descripción: ${it.medicion4}"
        } ?: "La información de la medición no está disponible."

        return AlertDialog.Builder(requireActivity()).apply {
            setTitle("Detalles de la Medición")
            setMessage(mensaje)
            setIcon(R.drawable.baseline_check_circle_outline_24)
            setNegativeButton("Cerrar", null)
        }.create()
    }
}
