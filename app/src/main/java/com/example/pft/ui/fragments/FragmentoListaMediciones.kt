package com.example.pft.ui.fragments

import AdaptadorMedicion
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.data.RepositorioMediciones
import com.example.pft.listeners.MedicionInteractionListener
import com.example.pft.models.Medicion

class FragmentoListaMediciones : Fragment(), MedicionInteractionListener {

    private lateinit var adaptadorMedicion: AdaptadorMedicion

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmento_lista_mediciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val medicionesRecyclerView: RecyclerView = view.findViewById(R.id.medicionesRecyclerView)
        medicionesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adaptadorMedicion = AdaptadorMedicion(RepositorioMediciones.obtenerMediciones(), this)
        medicionesRecyclerView.adapter = adaptadorMedicion
    }

    // Implementación de los métodos de MedicionInteractionListener
    override fun onDelete(medicion: Medicion) {
        // Implementa la lógica para eliminar una medición
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Eliminar Medición")
            setIcon(R.drawable.ic_baseline_warning_24)
            setMessage("¿Estás seguro de que deseas eliminar esta medición?")
            setPositiveButton("Eliminar") { dialog, _ ->
                RepositorioMediciones.eliminarMedicion(medicion)
                adaptadorMedicion.notifyDataSetChanged()
            }
            setNegativeButton("Cancelar", null)
        }.show()
    }

    override fun onUpdate(medicion: Medicion) {
        adaptadorMedicion.actualizarMediciones(RepositorioMediciones.obtenerMediciones())
    }

    override fun onItemSelected(medicion: Medicion) {
        // Muestra el detalle de la medición seleccionada
        DetalleMedicionDialogFragment.newInstance(medicion).show(parentFragmentManager, "detalleMedicionDialog")
    }

    override fun onResume() {
        super.onResume()
    }

}
