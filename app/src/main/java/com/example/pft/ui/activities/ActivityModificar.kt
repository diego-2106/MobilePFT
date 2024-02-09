package com.example.pft.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.pft.R
import com.example.pft.models.ActividadDeCampoDTO
import com.example.pft.models.DatoMedidaDTO
import com.example.pft.models.DepartamentoDTO
import com.example.pft.models.ListaMedicionesDTO
import com.example.pft.models.LocalidadDTO
import com.example.pft.models.ModificarMedicionesDTO
import com.example.pft.rest.RestAPI_Client
import com.example.pft.rest.RestAPI_Interface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat

class ActivityModificar : AppCompatActivity() {

    private lateinit var spinnerDepartamentos: Spinner
    private lateinit var spinnerLocalidades: Spinner
    private lateinit var spinnerActividades: Spinner
    private lateinit var spinnerDatoMedida: Spinner
    private lateinit var saveButton: Button

    private lateinit var adapterDepartamentos: ArrayAdapter<DepartamentoDTO>
    private lateinit var adapterLocalidades: ArrayAdapter<LocalidadDTO>
    private lateinit var adapterActividades: ArrayAdapter<ActividadDeCampoDTO>
    private lateinit var adapterDatosMedida: ArrayAdapter<DatoMedidaDTO>

    private val departamentos = mutableListOf<DepartamentoDTO>()
    private val localidades = mutableListOf<LocalidadDTO>()
    private val actividadesDeCampo = mutableListOf<ActividadDeCampoDTO>()
    private val datosMedida = mutableListOf<DatoMedidaDTO>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_medicion)

        //Boton para volver (en el toolbar)
        val volver = findViewById<ImageButton>(R.id.volver)
        volver.setOnClickListener { onBackPressed() }

        spinnerDepartamentos = findViewById(R.id.spinnerDepartamentos)
        adapterDepartamentos = ArrayAdapter(this, android.R.layout.simple_spinner_item, departamentos)
        adapterDepartamentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDepartamentos.adapter = adapterDepartamentos

        spinnerLocalidades = findViewById(R.id.spinnerLocalidades)
        adapterLocalidades = ArrayAdapter(this, android.R.layout.simple_spinner_item, localidades)
        adapterLocalidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLocalidades.adapter = adapterLocalidades

        spinnerActividades = findViewById(R.id.spinnerActividades)
        adapterActividades = ArrayAdapter(this, android.R.layout.simple_spinner_item, actividadesDeCampo)
        adapterActividades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerActividades.adapter = adapterActividades

        spinnerDatoMedida = findViewById(R.id.spinnerDatoMedida)
        adapterDatosMedida = ArrayAdapter(this, android.R.layout.simple_spinner_item, datosMedida)
        adapterDatosMedida.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDatoMedida.adapter = adapterDatosMedida




        val intent = intent
        if (intent != null && intent.hasExtra("medicion")) {
            val medicion = intent.getSerializableExtra("medicion") as ListaMedicionesDTO

            // Llenar los EditText
            val medicionValor = findViewById<EditText>(R.id.medicionValor)
            val medicionFecha = findViewById<EditText>(R.id.medicionFecha)
            val medicionObservaciones = findViewById<EditText>(R.id.medicionObservaciones)

            medicionValor.setText(medicion.valor)
            medicionFecha.setText(medicion.fecha)
            medicionObservaciones.setText(medicion.observaciones)
        }


        //Boton de modificar
        val saveButton = findViewById<AppCompatButton>(R.id.saveButton)
        saveButton.setOnClickListener {
            val medicionId = intent.getLongExtra("medicionId", -1) //Obtenemos el ID de la medicion, esto se encuentra en MedicionesAdapter

            // Dialogo para confirmar
            AlertDialog.Builder(this)
                .setTitle("Confirmar Modificación")
                .setIcon(R.drawable.baseline_check_circle_outline_24)
                .setMessage("¿Estás seguro de que deseas modificar esta medición?")
                .setPositiveButton("ACEPTAR") { dialog, which ->

                    val departamentoSeleccionado = (spinnerDepartamentos.selectedItem as? DepartamentoDTO)?.idDepartamento
                    val localidadSeleccionada = (spinnerLocalidades.selectedItem as? LocalidadDTO)?.idLocalidad
                    val actividadSeleccionada = (spinnerActividades.selectedItem as? ActividadDeCampoDTO)?.idActividad
                    val datoMedidaSeleccionado = (spinnerDatoMedida.selectedItem as? DatoMedidaDTO)?.idDatoMedida
                    val valor = findViewById<EditText>(R.id.medicionValor).text.toString().trim()
                    val fecha = findViewById<EditText>(R.id.medicionFecha).text.toString().trim()
                    val observaciones = findViewById<EditText>(R.id.medicionObservaciones).text.toString().trim()

                    // Validar que los valores seleccionados no sean nulos
                    if (departamentoSeleccionado != null && localidadSeleccionada != null &&
                        actividadSeleccionada != null && datoMedidaSeleccionado != null) {
                        // Crear objeto MedicionesDTO con los datos requeridos, spinners y demas
                        val medicion = try {
                            val fechaParsed = SimpleDateFormat("dd-MM-yy").parse(fecha)
                            ModificarMedicionesDTO(
                                medicionId,
                                departamentoSeleccionado,
                                localidadSeleccionada,
                                valor,
                                fechaParsed,
                                observaciones,
                                actividadSeleccionada,
                                datoMedidaSeleccionado,
                            )
                        } catch (e: ParseException) {
                            null
                        }

                        // Enviamos la solicitud PUT al server
                        val restApi = RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
                        val call = restApi.updateRegistro(medicion)
                        call.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    // En caso de que sea exitoso se manda ese Toast
                                    Toast.makeText(
                                        applicationContext,
                                        "Datos modificados exitosamente",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    // Si hubo un error de algun tipo te tira ese toast
                                    Toast.makeText(
                                        applicationContext,
                                        "Error al modificar datos. Inténtalo de nuevo más tarde.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                // Si la solicitud al endpoint falla te da ese toast
                                Toast.makeText(
                                    applicationContext,
                                    "Error al modificar datos: ${t.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    } else {
                        // Validar que no sean nulos los campos
                        Toast.makeText(
                            applicationContext,
                            "Por favor, completa todos los campos antes de modificar.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .setNegativeButton("CANCELAR") { dialog, which ->
                    // Cerrar el dialog cuando se presiona cancelar
                    dialog.dismiss()
                }
                .show()
        }

        val restApi = RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
        val callDepartamentos = restApi.getDepartamentos()
        val callLocalidades = restApi.getLocalidades()
        val callActividades = restApi.getActividades()
        val callDatosMedida = restApi.getDatosMedida()

        callDepartamentos.enqueue(object : Callback<List<DepartamentoDTO>> {
            override fun onResponse(
                call: Call<List<DepartamentoDTO>>,
                response: Response<List<DepartamentoDTO>>)
            {
                if (response.isSuccessful) {
                    departamentos.clear()
                    departamentos.addAll(response.body() as List<DepartamentoDTO>)
                    adapterDepartamentos.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<DepartamentoDTO>>, t: Throwable) {
                // Manejar el error
            }
        })

        callLocalidades.enqueue(object : Callback<List<LocalidadDTO>> {
            override fun onResponse(
                call: Call<List<LocalidadDTO>>,
                response: Response<List<LocalidadDTO>>
            ) {
                if(response.isSuccessful) {
                    localidades.clear()
                    localidades.addAll(response.body() as List<LocalidadDTO>)
                    adapterLocalidades.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<LocalidadDTO>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        callActividades.enqueue(object : Callback<List<ActividadDeCampoDTO>> {
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

        callDatosMedida.enqueue(object : Callback<List<DatoMedidaDTO>>{
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