import androidx.fragment.app.DialogFragment
import com.example.pft.models.Medicion

class ActualizarMedicionDialogFragment : DialogFragment() {

    //interfaz para enviar datos al fragmento que abre este DialogFragment
    interface ActualizarMedicionListener {
        fun onMedicionUpdated(medicion: Medicion)
    }
}
