package com.example.pft.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.adapters.AdaptadorMedicion
import com.example.pft.data.RepositorioMediciones
import com.example.pft.data.RepositorioMediciones.actualizarMedicion
import com.example.pft.models.Medicion

class FragmentoListaMediciones : Fragment() {

    private lateinit var adaptadorMedicion: AdaptadorMedicion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmento_lista_mediciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val medicionesRecyclerView: RecyclerView = view.findViewById(R.id.medicionesRecyclerView)
        medicionesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adaptadorMedicion = AdaptadorMedicion(
            emptyList(),
            onDeleteClick = ::eliminarMedicion,
            onUpdateClick = ::actualizarMedicion,
            onItemSelected = ::mostrarDetallesMedicion
        )

        medicionesRecyclerView.adapter = adaptadorMedicion
        actualizarMediciones()
    }

    private fun eliminarMedicion(medicion: Medicion) {
        RepositorioMediciones.eliminarMedicion(medicion)
        actualizarMediciones()
    }

    private fun mostrarDetallesMedicion(medicion: Medicion) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Detalles de la Medición")
        builder.setIcon(R.drawable.ic_baseline_info_24)
        builder.setMessage("Medición 1: ${medicion.medicion1}\nMedición 2: ${medicion.medicion2}\nMedicion 3: ${medicion.medicion3}\nMedicion 4: ${medicion.medicion4}")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun actualizarMediciones() {
        val mediciones = RepositorioMediciones.obtenerMediciones()
        adaptadorMedicion.actualizarMediciones(mediciones)
    }

    override fun onResume() {
        super.onResume()
        actualizarMediciones()
    }
}
