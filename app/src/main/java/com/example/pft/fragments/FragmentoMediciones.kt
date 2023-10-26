package com.example.pft.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.adapters.AdaptadorMedicion
import com.example.pft.models.Medicion
import android.app.AlertDialog

class FragmentoMediciones : Fragment() {

    private lateinit var adaptadorMedicion: AdaptadorMedicion
    private val mediciones = mutableListOf<Medicion>()
    private var nextId = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmento_mediciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val medicion1EditText: EditText = view.findViewById(R.id.medicion1EditText)
        val medicion2EditText: EditText = view.findViewById(R.id.medicion2EditText)
        val saveButton: Button = view.findViewById(R.id.saveButton)
        val medicionesRecyclerView: RecyclerView = view.findViewById(R.id.medicionesRecyclerView)

        adaptadorMedicion = AdaptadorMedicion(
            mediciones,
            onDeleteClick = { medicion ->
                mediciones.remove(medicion)
                adaptadorMedicion.notifyDataSetChanged()
            },
            onItemSelected = { medicion ->
                showEditDialog(medicion)
            }
        )

        medicionesRecyclerView.adapter = adaptadorMedicion

        saveButton.setOnClickListener {
            val medicion1 = medicion1EditText.text.toString()
            val medicion2 = medicion2EditText.text.toString()
            if (medicion1.isNotEmpty() && medicion2.isNotEmpty()) {
                mediciones.add(Medicion(nextId++, medicion1, medicion2))
                adaptadorMedicion.notifyDataSetChanged()
            } else {
                // muestra mensaje de error en caso de que los campos estén vacíos
                AlertDialog.Builder(requireContext())
                    .setMessage("Ambos campos deben estar llenos")
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    private fun showEditDialog(medicion: Medicion) {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout: View = inflater.inflate(R.layout.dialog_edit_medicion, null)
        val editMedicion1 = dialogLayout.findViewById<EditText>(R.id.editMedicion1)
        val editMedicion2 = dialogLayout.findViewById<EditText>(R.id.editMedicion2)

        editMedicion1.setText(medicion.medicion1)
        editMedicion2.setText(medicion.medicion2)

        builder.setView(dialogLayout)
            .setPositiveButton("Guardar") { dialog, _ ->
                val newMedicion1 = editMedicion1.text?.toString()
                val newMedicion2 = editMedicion2.text?.toString()
                if (newMedicion1?.isNotEmpty() == true && newMedicion2?.isNotEmpty() == true) {
                    medicion.medicion1 = newMedicion1
                    medicion.medicion2 = newMedicion2
                    adaptadorMedicion.notifyDataSetChanged()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Eliminar") { dialog, _ ->
                mediciones.remove(medicion)
                adaptadorMedicion.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNeutralButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}
