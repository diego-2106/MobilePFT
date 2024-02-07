package com.example.pft.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pft.R
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.models.ActividadDeCampoDTO
import com.example.pft.models.DatoMedidaDTO
import com.example.pft.models.DepartamentoDTO
import com.example.pft.models.LocalidadDTO
import com.example.pft.models.MedicionesDTO
import com.example.pft.rest.RestAPI_Client
import com.example.pft.rest.RestAPI_Interface
import com.example.pft.ui.adapters.MedicionesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat


class FragmentoMediciones : Fragment() {

    private lateinit var spinnerDepartamento: Spinner
    private lateinit var spinnerLocalidad: Spinner
    private lateinit var spinnerActividad: Spinner
    private lateinit var spinnerDatoMedidas: Spinner

    private lateinit var saveButton: Button

    private lateinit var medicionValor: EditText
    private lateinit var medicionFecha: EditText
    private lateinit var medicionObservaciones: EditText

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

        spinnerDepartamento = view.findViewById(R.id.spinnerDepartamentos)
        spinnerLocalidad = view.findViewById(R.id.spinnerLocalidades)
        spinnerActividad = view.findViewById(R.id.spinnerActividades)
        spinnerDatoMedidas = view.findViewById(R.id.spinnerDatoMedida)
        medicionValor = view.findViewById(R.id.medicionValor)
        medicionFecha = view.findViewById(R.id.medicionFecha)
        medicionObservaciones = view.findViewById(R.id.medicionObservaciones)

        saveButton = view.findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            // Obtener los datos de los campos
            val departamentoId =
                (spinnerDepartamento.selectedItem as? DepartamentoDTO)?.idDepartamento
            val localidadId = (spinnerLocalidad.selectedItem as? LocalidadDTO)?.idLocalidad
            val actividadId = (spinnerActividad.selectedItem as? ActividadDeCampoDTO)?.idActividad
            val datoMedidaId = (spinnerDatoMedidas.selectedItem as? DatoMedidaDTO)?.idDatoMedida
            val valor = medicionValor.text.toString().trim()
            val fecha = medicionFecha.text.toString().trim()
            val observaciones = medicionObservaciones.text.toString().trim()

            // Verificar si algún campo está vacío
            if (departamentoId == null || localidadId == null || actividadId == null || datoMedidaId == null || valor.isEmpty() || fecha.isEmpty() || observaciones.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Todos los campos deben estar llenos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Crear objeto MedicionesDTO
                val medicion = try {
                    val fechaParsed = SimpleDateFormat("dd-MM-yy").parse(fecha)
                    MedicionesDTO(
                        departamentoId,
                        localidadId,
                        valor,
                        fechaParsed,
                        observaciones,
                        actividadId,
                        datoMedidaId
                    )
                } catch (e: ParseException) {
                    null
                }

                if (medicion != null) {
                    // Mostrar diálogo de confirmación
                    val confirmDialog = AlertDialog.Builder(requireContext())
                        .setTitle("Confirmación")
                        .setMessage("¿Estás seguro de que deseas agregar esta medición?")
                        .setIcon(R.drawable.baseline_check_circle_outline_24)
                        .setPositiveButton("Aceptar") { _, _ ->
                            // Realizar la llamada al servidor para agregar la medición
                            val restApi =
                                RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
                            val call = restApi.addRegistro(medicion)
                            call.enqueue(object : Callback<Void> {
                                override fun onResponse(
                                    call: Call<Void>,
                                    response: Response<Void>
                                ) {
                                    if (response.isSuccessful) {
                                        // La medición se ha agregado exitosamente
                                        Toast.makeText(
                                            requireContext(),
                                            "Medición agregada correctamente",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        // La solicitud al servidor fue exitosa pero la medición no se agregó correctamente
                                        Toast.makeText(
                                            requireContext(),
                                            "Error al agregar la medición",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    // La solicitud al servidor falló
                                    Toast.makeText(
                                        requireContext(),
                                        "Error de red al agregar la medición",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                        }
                        .setNegativeButton("Cancelar") { dialog, _ ->
                            dialog.dismiss() // Cerrar el diálogo
                        }
                        .create()

                    confirmDialog.show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error al convertir la fecha",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}






