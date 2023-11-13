import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pft.R
import com.example.pft.listeners.MedicionInteractionListener
import com.example.pft.models.Medicion
import com.example.pft.viewholder.MedicionViewHolder

class AdaptadorMedicion(
    private var mediciones: List<Medicion>,
    private val listener: MedicionInteractionListener
) : RecyclerView.Adapter<MedicionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_medicion, parent, false)
        return MedicionViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: MedicionViewHolder, position: Int) {
        holder.bind(mediciones[position])
    }

    override fun getItemCount(): Int = mediciones.size

    fun actualizarMediciones(nuevasMediciones: List<Medicion>) {
        mediciones = nuevasMediciones
        notifyDataSetChanged()
    }
}