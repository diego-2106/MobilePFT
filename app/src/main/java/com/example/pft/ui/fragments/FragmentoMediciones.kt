package com.example.pft.ui.fragments

import AdaptadorMedicion
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.models.Medicion
import android.app.AlertDialog
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.pft.data.RepositorioMediciones
import com.example.pft.listeners.MedicionInteractionListener
import com.example.pft.models.ActividadDeCampoDTO
import com.example.pft.models.DatoMedidaDTO
import com.example.pft.models.DepartamentoDTO
import com.example.pft.models.LocalidadDTO
import com.example.pft.rest.RestAPI_Client
import com.example.pft.rest.RestAPI_Interface
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentoMediciones : Fragment() {

    private lateinit var adaptadorMedicion: AdaptadorMedicion
    private val mediciones = mutableListOf<Medicion>()
    private var nextId = 1

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

        val restApiActividades = RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
        val call2 = restApiActividades.getActividades()

        val restApiDatoMedida = RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
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
                if(response.isSuccessful)
                    actividadesDeCampo.clear()
                    actividadesDeCampo.addAll(response.body() as List<ActividadDeCampoDTO>)
                    adapterActividades.notifyDataSetChanged()
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
                if (response.isSuccessful)
                    datosMedida.clear()
                    datosMedida.addAll(response.body() as List<DatoMedidaDTO>)
                    adapterDatosMedida.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<DatoMedidaDTO>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


        val medicion1EditText: EditText = view.findViewById(R.id.medicionValor)
        val saveButton: Button = view.findViewById(R.id.saveButton)
        val medicionesRecyclerView: RecyclerView = view.findViewById(R.id.medicionesRecyclerView)
        medicionesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adaptadorMedicion = AdaptadorMedicion(
            mediciones,
            object : MedicionInteractionListener {
                override fun onDelete(medicion: Medicion) {
                    mostrarDialogoEliminar(medicion)
                }

                override fun onUpdate(medicion: Medicion) {
                    mostrarDialogoActualizar(medicion)
                }

                override fun onItemSelected(medicion: Medicion) {
                    mostrarDetallesMedicion(medicion)
                }
            }
        )
        medicionesRecyclerView.adapter = adaptadorMedicion

     /*  saveButton.setOnClickListener {
            guardarMedicion(
                medicion1EditText.text.toString()
            )
        } */
    }

  /*  private fun guardarMedicion(
        medicion1: String
    ) {
        if (medicion1.isNotEmpty()) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Guardar Medición")
            builder.setIcon(R.drawable.baseline_check_circle_outline_24)
            builder.setMessage("¿Estás seguro de que deseas guardar esta medición?")
            builder.setPositiveButton("Guardar") { dialog, _ ->
                // Código para guardar la medición
                val nuevaMedicion = Medicion(nextId++, medicion1)
                RepositorioMediciones.agregarMedicion(nuevaMedicion)
                mediciones.add(nuevaMedicion)
                adaptadorMedicion.notifyItemInserted(mediciones.size - 1)
            }
            builder.setNegativeButton("Cancelar", null)

            builder.show()
        } else {
            mostrarMensajeError()
        }
    }
    */

    private fun mostrarDialogoEliminar(medicion: Medicion) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Eliminar Medición")
            setMessage("¿Estás seguro de que deseas eliminar esta medición?")
            setPositiveButton("Eliminar") { _, _ ->
                eliminarMedicion(medicion)
            }
            setNegativeButton("Cancelar", null)
        }.create().show()
    }

    private fun eliminarMedicion(medicion: Medicion) {
        val index = mediciones.indexOf(medicion)
        if (index != -1) {
            RepositorioMediciones.eliminarMedicion(medicion)
            mediciones.removeAt(index)
            adaptadorMedicion.notifyItemRemoved(index)
        }
    }

    private fun mostrarDialogoActualizar(medicion: Medicion) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_medicion, null)
        val editMedicion1EditText: EditText = dialogView.findViewById(R.id.editMedicion1)
        val editMedicion2EditText: EditText = dialogView.findViewById(R.id.editMedicion2)

        editMedicion1EditText.setText(medicion.medicion1)
        editMedicion2EditText.setText(medicion.medicion2)

        AlertDialog.Builder(requireContext()).apply {
            setTitle("Actualizar Medición")
            setView(dialogView)
            setPositiveButton("Guardar") { dialog, _ ->
                // Aquí se recogen los datos editados y se actualiza la medición
                val nuevaMedicion1 = editMedicion1EditText.text.toString()
                val nuevaMedicion2 = editMedicion2EditText.text.toString()
                // Recoge los datos para medicion3 y medicion4

                //verifica que los datos son válidos antes de continuar
                medicion.medicion1 = nuevaMedicion1
                medicion.medicion2 = nuevaMedicion2
                // Actualiza medicion3 y medicion4

                RepositorioMediciones.actualizarMedicion(medicion)
                actualizarMediciones()
            }
            setNegativeButton("Cancelar", null)
        }.show()
    }

    private fun mostrarDetallesMedicion(medicion: Medicion?) {
        val mensaje = if (medicion != null) {
            "Detalles de la medición:\n" +
                    "Medición 1: ${medicion.medicion1}\n" +
                    "Medición 2: ${medicion.medicion2}\n"
        } else {
            "La información de la medición no está disponible."
        }

        AlertDialog.Builder(requireContext()).apply {
            setTitle("Detalles de la Medición")
            setMessage(mensaje)
            setPositiveButton("Cerrar", null)
        }.show()
    }

    private fun mostrarMensajeError() {
        AlertDialog.Builder(requireContext())
            .setTitle("Atención!")
            .setMessage("Ambos campos deben estar llenos")
            .setIcon(R.drawable.ic_baseline_warning_24)
            .setPositiveButton("Ok", null)
            .show()
    }

    private fun actualizarMediciones() {
        val nuevasMediciones = RepositorioMediciones.obtenerMediciones()
        mediciones.clear()
        mediciones.addAll(nuevasMediciones)
        adaptadorMedicion.notifyDataSetChanged()
    }
}





