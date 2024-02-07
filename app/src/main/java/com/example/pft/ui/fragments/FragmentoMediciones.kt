package com.example.pft.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pft.R
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.pft.models.ActividadDeCampoDTO
import com.example.pft.models.DatoMedidaDTO
import com.example.pft.models.DepartamentoDTO
import com.example.pft.models.LocalidadDTO
import com.example.pft.rest.RestAPI_Client
import com.example.pft.rest.RestAPI_Interface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentoMediciones : Fragment() {

    private lateinit var spinnerDepartamento: Spinner
    private lateinit var spinnerLocalidades: Spinner
    private lateinit var spinnerActividades: Spinner
    private lateinit var spinnerDatoMedida: Spinner

    private lateinit var adapterDatosMedida: ArrayAdapter<DatoMedidaDTO>
    private val datosMedida = mutableListOf<DatoMedidaDTO>()

    private lateinit var adapter: ArrayAdapter<DepartamentoDTO>
    private val departamentos = mutableListOf<DepartamentoDTO>()

    private lateinit var adapterLocalidad: ArrayAdapter<LocalidadDTO>
    private val localidades = mutableListOf<LocalidadDTO>()

    private lateinit var adapterActividades: ArrayAdapter<ActividadDeCampoDTO>
    private val actividadesDeCampo = mutableListOf<ActividadDeCampoDTO>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmento_mediciones, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapterDatosMedida = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            datosMedida
        )

        adapterDatosMedida.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinnerDatoMedida: Spinner = view.findViewById(R.id.spinnerDatoMedida)
        spinnerDatoMedida.adapter = adapterDatosMedida

        //Spinner Localidades
        adapterLocalidad = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            localidades
        )

        adapterLocalidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinnerLocalidades: Spinner = view.findViewById(R.id.spinnerLocalidades)
        spinnerLocalidades.adapter = adapterLocalidad


        //Departamentos Spinner
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            departamentos
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerDepartamentos: Spinner = view.findViewById(R.id.spinnerDepartamentos)
        spinnerDepartamentos.adapter = adapter

        //Spinner ActividadDeCampo
        adapterActividades = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            actividadesDeCampo
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerActividades: Spinner = view.findViewById(R.id.spinnerActividades)
        spinnerActividades.adapter = adapterActividades

        val restApi = RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
        val call = restApi.getDepartamentos()

        val restApiLocalidad = RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
        val call1 = restApiLocalidad.getLocalidades()

        val restApiActividades =
            RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
        val call2 = restApiActividades.getActividades()

        val restApiDatoMedida =
            RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
        val call3 = restApiDatoMedida.getDatosMedida()


        //Se rellena el spinner de Dptos
        call.enqueue(object : Callback<List<DepartamentoDTO>> {
            override fun onResponse(
                call: Call<List<DepartamentoDTO>>,
                response: Response<List<DepartamentoDTO>>
            ) {
                if (response.isSuccessful) {
                    departamentos.clear()
                    departamentos.addAll(response.body() as List<DepartamentoDTO>)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<DepartamentoDTO>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        //Se rellena el spinner de localidades
        call1.enqueue(object : Callback<List<LocalidadDTO>> {
            override fun onResponse(
                call: Call<List<LocalidadDTO>>,
                response: Response<List<LocalidadDTO>>
            ) {
                if (response.isSuccessful) {
                    localidades.clear()
                    localidades.addAll(response.body() as List<LocalidadDTO>)
                    adapterLocalidad.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<LocalidadDTO>>, t: Throwable) {
                // Manejar el fallo de manera adecuada
            }
        })

        //CallActividades - Se rellena es spinner de ADC
        call2.enqueue(object : Callback<List<ActividadDeCampoDTO>> {
            override fun onResponse(
                call: Call<List<ActividadDeCampoDTO>>,
                response: Response<List<ActividadDeCampoDTO>>
            ) {
                if (response.isSuccessful) {
                    actividadesDeCampo.clear()
                    actividadesDeCampo.addAll(response.body() as List<ActividadDeCampoDTO>)
                    adapterActividades.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<List<ActividadDeCampoDTO>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        call3.enqueue(object : Callback<List<DatoMedidaDTO>> {
            override fun onResponse(
                call: Call<List<DatoMedidaDTO>>,
                response: Response<List<DatoMedidaDTO>>
            ) {
                if (response.isSuccessful) {
                    datosMedida.clear()
                    datosMedida.addAll(response.body() as List<DatoMedidaDTO>)
                    adapterDatosMedida.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<List<DatoMedidaDTO>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

}





