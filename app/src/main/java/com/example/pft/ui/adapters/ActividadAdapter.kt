package com.example.pft.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.models.ActividadDeCampoDTO

class ActividadAdapter(private val actividades: List<ActividadDeCampoDTO>) : RecyclerView.Adapter<ActividadAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreFormulario: TextView = itemView.findViewById(R.id.nombreFormularioTextView)
        val idActividad: TextView = itemView.findViewById(R.id.idActividadTextView)
        val nombreUsuario: TextView = itemView.findViewById(R.id.nombreUsuarioTextView)
        // Puedes agregar más vistas según tus necesidades
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actividad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actividad = actividades[position]

        // Verificar si el formulario no es nulo antes de acceder a sus métodos
        val formulario = actividad.formulario
        if (actividad.usuario != null && actividad.formulario != null) {
            // Configurar tus vistas según los datos de ActividadDeCampoDTO
            holder.nombreUsuario.text = actividad.usuario?.nombre?: "Nombre de Usuario no disponible"
            holder.nombreFormulario.text = actividad.formulario?.nombre ?: "formulario no disponible"
            // Puedes configurar más vistas aquí
        } else {

        }

        // Puedes agregar clics u otros eventos según tus necesidades
    }

    override fun getItemCount(): Int {
        return actividades.size
    }
}