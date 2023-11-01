package com.example.pft.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.models.Medicion

class AdaptadorMedicion(

    private var mediciones: List<Medicion>,
    val onDeleteClick: (Medicion) -> Unit,
    val onItemSelected: (Medicion) -> Unit
) : RecyclerView.Adapter<AdaptadorMedicion.MedicionViewHolder>() {

    inner class MedicionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val medicion1TextView: TextView = itemView.findViewById(R.id.medicion1TextView)
        private val medicion2TextView: TextView = itemView.findViewById(R.id.medicion2TextView)

        fun bind(medicion: Medicion) {
            medicion1TextView.text = medicion.medicion1
            medicion2TextView.text = medicion.medicion2
            itemView.setOnClickListener {
                onItemSelected(medicion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medicion, parent, false)
        return MedicionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MedicionViewHolder, position: Int) {
        val medicion = mediciones[position]
        holder.bind(medicion)
    }

    override fun getItemCount(): Int {
        return mediciones.size
    }

    // Método para actualizar las mediciones y notificar al RecyclerView
    fun actualizarMediciones(nuevasMediciones: List<Medicion>) {
        mediciones = nuevasMediciones
        notifyDataSetChanged()  // Notificar cambios para que el RecyclerView se actualice
    }
}
