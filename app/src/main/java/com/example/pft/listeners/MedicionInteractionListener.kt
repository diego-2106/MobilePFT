package com.example.pft.listeners

import com.example.pft.models.Medicion

interface MedicionInteractionListener {
    fun onDelete(medicion: Medicion)
    fun onUpdate(medicion: Medicion)
    fun onItemSelected(medicion: Medicion)
}