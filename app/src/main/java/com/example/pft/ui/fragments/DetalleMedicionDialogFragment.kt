package com.example.pft.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
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
                    "Medición 1: ${it.medicion1}\n" +
                    "Medición 2: ${it.medicion2}\n" +
                    "Medición 3: ${it.medicion3}\n" +
                    "Medición 4: ${it.medicion4}"
        } ?: "La información de la medición no está disponible."

        return AlertDialog.Builder(requireActivity()).apply {
            setTitle("Detalles de la Medición")
            setMessage(mensaje)
            setPositiveButton("Cerrar", null)
        }.create()
    }
}
