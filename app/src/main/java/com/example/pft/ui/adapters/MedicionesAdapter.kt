package com.example.pft.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.models.MedicionesDTO
import java.text.SimpleDateFormat
import java.util.*

class MedicionesAdapter(private val medicionesList: List<MedicionesDTO>) :
    RecyclerView.Adapter<MedicionesAdapter.MedicionesViewHolder>() {

    class MedicionesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvValor: TextView = view.findViewById(R.id.medicionValor)
        val tvFecha: TextView = view.findViewById(R.id.medicionFecha)
        val tvObservacion: TextView = view.findViewById(R.id.medicionObservaciones)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicionesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medicion, parent, false)
        return MedicionesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicionesViewHolder, position: Int) {
        val medicion = medicionesList[position]
        holder.tvValor.text = medicion.valor // Asegúrate de que 'valor' es un campo de MedicionesDTO
        holder.tvFecha.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(medicion.fecha)
        holder.tvObservacion.text = medicion.observaciones
    }

    override fun getItemCount() = medicionesList.size
}
