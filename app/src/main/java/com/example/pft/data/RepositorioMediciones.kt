package com.example.pft.data

import com.example.pft.models.Medicion

object RepositorioMediciones {

    private val mediciones = mutableListOf<Medicion>()
    private var nextId = 1  // Inicializa un contador de ID

    fun agregarMedicion(medicion: Medicion) {
        mediciones.add(medicion)
    }

    fun obtenerMediciones(): List<Medicion> {
        return mediciones
    }

    fun generarId(): Int {
        return nextId++  // Incrementa el contador para el próximo ID
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
