import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.models.ActividadDeCampoDTO
import com.example.pft.rest.RestAPI_Client
import com.example.pft.rest.RestAPI_Interface
import com.example.pft.ui.adapters.ActividadAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListadoActividades : Fragment() {

    private val actividades: MutableList<ActividadDeCampoDTO> = mutableListOf()
    private lateinit var adapter: ActividadAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmento_listado_actividades, container, false)
        adapter = ActividadAdapter(actividades)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewActividades)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter  // Asigna el adaptador al RecyclerView

        val call: Call<List<ActividadDeCampoDTO>> = RestAPI_Client.retrofitInstance.create(RestAPI_Interface::class.java).getActividades()

        call.enqueue(object : Callback<List<ActividadDeCampoDTO>> {
            override fun onResponse(
                call: Call<List<ActividadDeCampoDTO>>,
                response: Response<List<ActividadDeCampoDTO>>
            ) {
                if (response.isSuccessful) {
                    actividades.clear()
                    actividades.addAll(response.body() ?: emptyList())

                    // Notifica cambios en el hilo principal
                    requireActivity().runOnUiThread {
                        if (adapter != null) {
                            adapter.notifyDataSetChanged()
                        }
                    }

                } else {
                    // Manejar errores de la respuesta si es necesario
                }
            }

            override fun onFailure(call: Call<List<ActividadDeCampoDTO>>, t: Throwable) {
                Log.e("ListadoActividades", "Error en la llamada a la API", t)

            }
        })

        return view
    }
}