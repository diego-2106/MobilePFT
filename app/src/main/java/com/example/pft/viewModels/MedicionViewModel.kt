package com.example.pft.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pft.data.RepositorioMediciones
import com.example.pft.models.Medicion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicionViewModel(private val repositorioMediciones: RepositorioMediciones) : ViewModel() {

    fun agregarMedicion(medicion: Medicion) {
        viewModelScope.launch(Dispatchers.IO) {
            repositorioMediciones.agregarMedicion(medicion)
        }
    }

    fun generarId(): Int {
        return repositorioMediciones.generarId()
    }
}