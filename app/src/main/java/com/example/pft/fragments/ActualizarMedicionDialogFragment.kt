import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.pft.models.Medicion

class ActualizarMedicionDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Obtener la medición del argumento
            val medicion = arguments?.getSerializable("medicion") as? Medicion
            // Configurar el mensaje del dialog con los detalles de la medición
            builder.setMessage("Detalles de la medición: \n${medicion.toString()}")
                .setPositiveButton("Cerrar") { dialog, id ->
                    // Cierra el diálogo
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
