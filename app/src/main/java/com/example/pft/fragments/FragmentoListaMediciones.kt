package com.example.pft.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.adapters.AdaptadorMedicion
import com.example.pft.data.RepositorioMediciones

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

        adaptadorMedicion = AdaptadorMedicion(
            RepositorioMediciones.obtenerMediciones(),
            onDeleteClick = { medicion ->
                RepositorioMediciones.eliminarMedicion(medicion)
                actualizarMediciones()
            },
            onItemSelected = { medicion ->
                // Aquí podrías, por ejemplo, navegar al FragmentoEntradaMediciones
                // pasando la medicion seleccionada como argumento.
            }
        )

        medicionesRecyclerView.adapter = adaptadorMedicion
    }

    override fun onResume() {
        super.onResume()
        actualizarMediciones()
    }

    private fun actualizarMediciones() {
        adaptadorMedicion.actualizarMediciones(RepositorioMediciones.obtenerMediciones())
    }
}
