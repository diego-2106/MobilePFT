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
import com.example.pft.data.RepositorioMediciones

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
        val medicion3EditText: EditText = view.findViewById(R.id.medicion3EditText)
        val medicion4EditText: EditText = view.findViewById(R.id.medicion4EditText)
        val saveButton: Button = view.findViewById(R.id.saveButton)
        val medicionesRecyclerView: RecyclerView = view.findViewById(R.id.medicionesRecyclerView)

        adaptadorMedicion = AdaptadorMedicion(
            mediciones,
            // ... código para manejar el borrado ...
            onDeleteClick = { medicion ->
                mediciones.remove(medicion)
                adaptadorMedicion.notifyDataSetChanged()
            },
            onUpdateClick = { medicion ->
                // Aquí, implementa lo que debe suceder cuando se hace clic en actualizar
                showEditDialog(medicion)
            },
            // ... código para manejar la selección de un elemento ...
            onItemSelected = { medicion ->
                showEditDialog(medicion)
            }
        )

        medicionesRecyclerView.adapter = adaptadorMedicion

        saveButton.setOnClickListener {
            val medicion1 = medicion1EditText.text.toString()
            val medicion2 = medicion2EditText.text.toString()
            val medicion3 = medicion3EditText.text.toString()
            val medicion4 = medicion4EditText.text.toString()
            if (medicion1.isNotEmpty() && medicion2.isNotEmpty()) {
                val medicion = Medicion(nextId++, medicion1, medicion2, medicion3, medicion4)
                RepositorioMediciones.agregarMedicion(medicion)
                mediciones.add(medicion) // Agrega la nueva medición a la lista local
                adaptadorMedicion.notifyItemInserted(mediciones.size - 1) // Notificar al adaptador sobre la nueva medición
            } else {
                // muestra mensaje de error en caso de que los campos estén vacíos
                AlertDialog.Builder(requireContext())
                    .setTitle("Atención!")
                    .setMessage("Ambos campos deben estar llenos")
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    private fun showEditDialog(medicion: Medicion) {
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_edit_medicion, null)
        val editMedicion1 = dialogLayout.findViewById<EditText>(R.id.editMedicion1)
        val editMedicion2 = dialogLayout.findViewById<EditText>(R.id.editMedicion2)
        val editMedicion3 = dialogLayout.findViewById<EditText>(R.id.editMedicion3)
        val editMedicion4 = dialogLayout.findViewById<EditText>(R.id.editMedicion4)

        // Configurar los EditText con los valores actuales de la medicion
        editMedicion1.setText(medicion.medicion1)
        editMedicion2.setText(medicion.medicion2)
        editMedicion3.setText(medicion.medicion3)
        editMedicion4.setText(medicion.medicion4)


        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogLayout)
            .setPositiveButton("Guardar") { dialog, _ ->
                val newMedicion1 = editMedicion1.text?.toString()
                val newMedicion2 = editMedicion2.text?.toString()
                val newMedicion3 = editMedicion3.text?.toString()
                val newMedicion4 = editMedicion4.text?.toString()
                if (newMedicion1?.isNotEmpty() == true && newMedicion2?.isNotEmpty() == true && newMedicion3?.isNotEmpty() == true && newMedicion4?.isNotEmpty() == true) {
                    medicion.medicion1 = newMedicion1
                    medicion.medicion2 = newMedicion2
                    medicion.medicion3 = newMedicion3
                    medicion.medicion4 = newMedicion4
                    // Aquí asumo que necesitas actualizar la lista y luego notificar al adaptador
                    val index = mediciones.indexOf(medicion)
                    if (index != -1) {
                        mediciones[index] = medicion
                        adaptadorMedicion.notifyItemChanged(index)
                    }

                }
                dialog.dismiss()
            }
            .setNegativeButton("Eliminar") { dialog, _ ->
                val index = mediciones.indexOf(medicion)
                if (index != -1) {
                    mediciones.removeAt(index)
                    adaptadorMedicion.notifyItemRemoved(index)
                }
                dialog.dismiss()
            }
            .setNeutralButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }
    }
}
