package com.example.pft.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.models.ListaMedicionesDTO
import com.example.pft.models.MedicionesDTO
import com.example.pft.ui.activities.ActivityModificar
import java.text.SimpleDateFormat
import java.util.*

class MedicionesAdapter(
    private val medicionesList: List<ListaMedicionesDTO>
) : RecyclerView.Adapter<MedicionesAdapter.MedicionesViewHolder>() {

    class MedicionesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.medicionId)
        val tvValor: TextView = view.findViewById(R.id.medicionValor)
        val tvFecha: TextView = view.findViewById(R.id.medicionFecha)
        val btnModificar: TextView = view.findViewById(R.id.botonModificar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicionesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medicion, parent, false)
        return MedicionesViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: MedicionesViewHolder, position: Int) {
        val medicion = medicionesList[position]
        holder.tvValor.text = medicion.valor
        holder.tvFecha.text = medicion.fecha
        holder.tvId.text = medicion.idMedicion.toString()

        //Buscamos el Id de la medicion y seteamos medicion y medicionID.
        // El medicion es para los datos generales y el Id nos sirve para modificar
        //Es como poner un setAttribute en java
        val medicionId = medicion?.idMedicion
        holder.btnModificar.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ActivityModificar::class.java)
            intent.putExtra("medicion", medicion)
            intent.putExtra("medicionId", medicionId)

            context.startActivity(intent)
        }

        // Solo para el log
        Log.d("MedicionesAdapter", "Valor: ${medicion.valor}, Fecha: ${medicion.fecha}, Observaciones: ${medicion.observaciones}")
    }

    override fun getItemCount() = medicionesList.size
}

