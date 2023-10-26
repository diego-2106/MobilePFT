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
    private val mediciones: MutableList<Medicion>,
    val onDeleteClick: (Medicion) -> Unit,
    val onItemSelected: (Medicion) -> Unit 
) : RecyclerView.Adapter<AdaptadorMedicion.MedicionViewHolder>() {

    inner class MedicionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val medicion1View: TextView = itemView.findViewById(R.id.medicion1View)
        val medicion2View: TextView = itemView.findViewById(R.id.medicion2View)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicionViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_medicion, parent, false)
        return MedicionViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicionViewHolder, position: Int) {
        val medicion = mediciones[position]
        holder.medicion1View.text = medicion.medicion1
        holder.medicion2View.text = medicion.medicion2
        holder.itemView.setOnClickListener { onItemSelected(medicion) }  // Aquí se llama al callback cuando se hace clic en un ítem
        holder.deleteButton.setOnClickListener { onDeleteClick(medicion) }
    }

    override fun getItemCount() = mediciones.size
}
