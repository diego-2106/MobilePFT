package com.example.pft.ui.activities

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pft.R
import com.github.barteksc.pdfviewer.PDFView

class AbrirManual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abrir_manual)

        val pdfView = findViewById<PDFView>(R.id.pdf)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val textView = findViewById<TextView>(R.id.text_view_progress)

        // Boton de volver hacia atras, vuelve a la navegacion
        val volver = findViewById<ImageButton>(R.id.volver)
        volver.setOnClickListener { onBackPressed() }

        // Agrego un intervalo de 2 segundos para después mostrar el PDF
        Handler().postDelayed({
            // Muestro el PDF
            pdfView.visibility = View.VISIBLE
            pdfView.fromAsset("ManualUsuario.pdf")
                .defaultPage(0)
                .spacing(5)
                .enableAntialiasing(true)
                .load()
            progressBar.visibility = View.GONE
            textView.visibility = View.GONE
        }, 1000)
    }
}