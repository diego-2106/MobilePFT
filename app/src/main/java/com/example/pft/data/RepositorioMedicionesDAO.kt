package com.example.pft.data

import com.example.pft.models.MedicionDTO
import java.sql.Connection
import java.sql.DriverManager

class RepositorioMedicionesDAO: MedicionDAO {

    private val url = "jdbc:oracle:thin:@tu_host:puerto:db"
    private val username = "usuario"
    private val password = "contraseña"


    override fun obtenerTodasLasMediciones(): List<MedicionDTO> {
        // Implementación para obtener todas las mediciones de la BD
        val mediciones = mutableListOf<MedicionDTO>()
        val connection: Connection = DriverManager.getConnection(url, username, password)
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT id, fecha, valor, unidad FROM mediciones")

        while (resultSet.next()) {
            val medicion = MedicionDTO(
                id = resultSet.getInt("id"),
                medicion1 = resultSet.getString("medicion1"),
                medicion2 = resultSet.getString("medicion1"),
                medicion3 = resultSet.getString("medicion1"),
                medicion4 = resultSet.getString("medicion1")
            )
            mediciones.add(medicion)
        }

        resultSet.close()
        statement.close()
        connection.close()

        return mediciones
    }

    override fun obtenerMedicionPorId(id: Int): MedicionDTO? {
        TODO("Not yet implemented")
    }

    override fun insertarMedicion(medicion: MedicionDTO) {
        TODO("Not yet implemented")
    }

    override fun actualizarMedicion(medicion: MedicionDTO) {
        TODO("Not yet implemented")
    }

    override fun eliminarMedicion(id: Int) {
        TODO("Not yet implemented")
    }

    /*
        override fun obtenerMedicionPorId(id: Int): MedicionDTO? {
            // Implementación para obtener una medición por ID
        }

        override fun insertarMedicion(medicion: MedicionDTO) {
            // Implementación para insertar una nueva medición
        }

        override fun actualizarMedicion(medicion: MedicionDTO) {
            // Implementación para actualizar una medición existente
        }

        override fun eliminarMedicion(id: Int) {
            // Implementación para eliminar una medición
        }*/

}