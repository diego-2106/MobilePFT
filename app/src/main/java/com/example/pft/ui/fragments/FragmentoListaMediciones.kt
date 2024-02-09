package com.example.pft.ui.fragments



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.models.DatoMedidaDTO
import com.example.pft.models.ListaMedicionesDTO
import com.example.pft.models.MedicionesDTO
import com.example.pft.ui.adapters.MedicionesAdapter
import com.example.pft.rest.RestAPI_Client
import com.example.pft.rest.RestAPI_Interface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentoListaMediciones : Fragment() {

    private lateinit var medicionesRecyclerView: RecyclerView
    private lateinit var medicionesAdapter: MedicionesAdapter
    private var medicionesList = mutableListOf<ListaMedicionesDTO>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflamos el layout
        return inflater.inflate(R.layout.fragmento_lista_mediciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Configura el RecyclerView
        medicionesRecyclerView = view.findViewById(R.id.medicionesRecyclerView)
        medicionesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        medicionesAdapter = MedicionesAdapter(medicionesList)
        medicionesRecyclerView.adapter = medicionesAdapter

        // Obtenemos la lista de mediciones desde la API
        val restApi = RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
        val call = restApi.getMediciones()

        //Se configura el llamado y la respuesta, se actualizan los datos del listado y demas.
        call.enqueue(object : Callback<List<ListaMedicionesDTO>> {
            override fun onResponse(call: Call<List<ListaMedicionesDTO>>, response: Response<List<ListaMedicionesDTO>>) {
                if (response.isSuccessful) {
                    Log.d("API_CALL", "La llamada a la API fue exitosa.")
                    Toast.makeText(requireContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
                    medicionesList.clear()
                    medicionesList.addAll(response.body() ?: emptyList())
                    Log.d("API_RESPONSE_BODY", "Response body: ${response.body()}")
                    requireActivity().runOnUiThread() {
                        if (medicionesAdapter != null) {
                            medicionesAdapter.notifyDataSetChanged()
                            Toast.makeText(requireContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Log.e("API_CALL", "Error al obtener datos. Código de estado: ${response.code()}")
                    Toast.makeText(requireContext(), "Error al obtener datos", Toast.LENGTH_SHORT).show()
                }
            }

            // Dentro del bloque onFailure
            override fun onFailure(call: Call<List<ListaMedicionesDTO>>, t: Throwable) {
                Log.e("API_CALL", "Fallo al realizar la llamada a la API: ${t.message}")
                // Manejo de error, fallo al realizar la llamada a la API
            }
        })

    }
    }
