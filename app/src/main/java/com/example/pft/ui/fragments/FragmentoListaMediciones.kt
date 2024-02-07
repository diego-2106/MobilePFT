package com.example.pft.ui.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
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
    private var medicionesList = listOf<MedicionesDTO>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragmento_lista_mediciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura el RecyclerView
        medicionesRecyclerView = view.findViewById(R.id.medicionesRecyclerView)
        medicionesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        medicionesAdapter = MedicionesAdapter(medicionesList)
        medicionesRecyclerView.adapter = medicionesAdapter

        // Obtener las mediciones desde la API y actualizar la lista
        obtenerMediciones()
    }

    private fun obtenerMediciones() {
        val restApi = RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java)
        restApi.getMediciones().enqueue(object : Callback<List<MedicionesDTO>> {
            override fun onResponse(call: Call<List<MedicionesDTO>>, response: Response<List<MedicionesDTO>>) {
                if (response.isSuccessful) {
                    medicionesList = response.body() ?: emptyList()
                    medicionesAdapter = MedicionesAdapter(medicionesList)
                    medicionesRecyclerView.adapter = medicionesAdapter
                } else {
                    // Manejo de error, la respuesta no fue exitosa
                }
            }

            override fun onFailure(call: Call<List<MedicionesDTO>>, t: Throwable) {
                // Manejo de error, fallo al realizar la llamada a la API
            }
        })
    }
}
