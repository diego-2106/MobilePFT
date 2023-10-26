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

class FragmentoMediciones : Fragment() {
    private lateinit var adaptadorMedicion: AdaptadorMedicion
    private val mediciones = mutableListOf<Medicion>()
    private var nextId = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmento_mediciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val medicion1EditText: EditText = view.findViewById(R.id.medicion1EditText)
        val medicion2EditText: EditText = view.findViewById(R.id.medicion2EditText)
        val saveButton: Button = view.findViewById(R.id.saveButton)
        val medicionesRecyclerView: RecyclerView = view.findViewById(R.id.medicionesRecyclerView)

        adaptadorMedicion = AdaptadorMedicion(mediciones) { medicion ->
            mediciones.remove(medicion)
            adaptadorMedicion.notifyDataSetChanged()
        }
        medicionesRecyclerView.adapter = adaptadorMedicion

        saveButton.setOnClickListener {
            val medicion1 = medicion1EditText.text.toString()
            val medicion2 = medicion2EditText.text.toString()
            if (medicion1.isNotEmpty() && medicion2.isNotEmpty()) {
                mediciones.add(Medicion(nextId++, medicion1, medicion2))
                adaptadorMedicion.notifyDataSetChanged()
            }
        }
    }
}