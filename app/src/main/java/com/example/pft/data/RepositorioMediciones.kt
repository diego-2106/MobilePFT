package com.example.pft.data

import com.example.pft.models.Medicion

object RepositorioMediciones {

    private val mediciones = mutableListOf<Medicion>()
    private var nextId = 1

    fun agregarMedicion(medicion1: String, medicion2: String) {
        mediciones.add(Medicion(nextId++, medicion1, medicion2))
    }

    fun obtenerMediciones(): List<Medicion> {
        return mediciones
    }

    fun eliminarMedicion(medicion: Medicion) {
        mediciones.remove(medicion)
    }

    fun actualizarMedicion(medicion: Medicion) {
        val index = mediciones.indexOfFirst { it.id == medicion.id }
        if (index != -1) {
            mediciones[index] = medicion
        }
    }
}
