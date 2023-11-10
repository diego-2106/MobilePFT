package com.example.pft.viewsHolders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.listeners.MedicionInteractionListener
import com.example.pft.models.Medicion

class MedicionViewHolder(itemView: View, private val listener: MedicionInteractionListener) : RecyclerView.ViewHolder(itemView) {
    private val medicion1TextView: TextView = itemView.findViewById(R.id.medicion1TextView)
    private val medicion2TextView: TextView = itemView.findViewById(R.id.medicion2TextView)
    private val updateButton: Button = itemView.findViewById(R.id.updateButton)
    private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    private var currentMedicion: Medicion? = null

    init {
        updateButton.setOnClickListener { currentMedicion?.let { listener.onUpdate(it) } }
        deleteButton.setOnClickListener { currentMedicion?.let { listener.onDelete(it) } }
        itemView.setOnClickListener { currentMedicion?.let { listener.onItemSelected(it) } }
    }

    fun bind(medicion: Medicion) {
        currentMedicion = medicion
        medicion1TextView.text = medicion.medicion1
        medicion2TextView.text = medicion.medicion2
    }
}
