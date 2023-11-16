package com.example.pft.data

import com.example.pft.models.MedicionDTO

interface MedicionDAO {
    fun obtenerTodasLasMediciones(): List<MedicionDTO>
    fun obtenerMedicionPorId(id: Int): MedicionDTO?
    fun insertarMedicion(medicion: MedicionDTO)
    fun actualizarMedicion(medicion: MedicionDTO)
    fun eliminarMedicion(id: Int)
}