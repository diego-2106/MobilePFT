package com.example.pft.adapters

import ActualizarMedicionDialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.models.Medicion

class AdaptadorMedicion(
    private var mediciones: List<Medicion>,
    private val onDeleteClick: (Medicion) -> Unit,
    private val onUpdateClick: (Medicion) -> Unit,
    private val onItemSelected: (Medicion) -> Unit
) : RecyclerView.Adapter<AdaptadorMedicion.MedicionViewHolder>() {

    inner class MedicionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val medicion1TextView: TextView = itemView.findViewById(R.id.medicion1TextView)
        private val medicion2TextView: TextView = itemView.findViewById(R.id.medicion2TextView)
        private val updateButton: Button = itemView.findViewById(R.id.updateButton)
        private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

        fun bind(medicion: Medicion) {
            medicion1TextView.text = medicion.medicion1
            medicion2TextView.text = medicion.medicion2

            updateButton.setOnClickListener {
                val dialog = ActualizarMedicionDialogFragment()
                // Pasar 'medicion' actual al DialogFragment como Parcelable
                dialog.arguments = Bundle().apply {
                    putParcelable("medicion", medicion) //Medicion implementa Parcelable
                }
                dialog.show((itemView.context as FragmentActivity).supportFragmentManager, "ActualizarMedicion")
                onUpdateClick(medicion)
            }

            deleteButton.setOnClickListener {
                onDeleteClick(medicion)
            }

            itemView.setOnClickListener {
                onItemSelected(medicion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_medicion, parent, false)
        return MedicionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MedicionViewHolder, position: Int) {
        val medicion = mediciones[position]
        holder.bind(medicion)
    }

    override fun getItemCount(): Int {
        return mediciones.size
    }

    fun actualizarMediciones(nuevasMediciones: List<Medicion>) {
        mediciones = nuevasMediciones
        notifyDataSetChanged()
    }
}
