package com.example.relojdevida

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ScrollView // Asegúrate de importar ScrollView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class EnviarMensajeActivity : AppCompatActivity() {

    // Inicialización de las vistas
    private lateinit var ivProfilePicture: ImageView
    private lateinit var tvUserName: TextView
    private lateinit var etMessage: EditText
    private lateinit var btnInstagram: Button
    private lateinit var btnFacebook: Button
    private lateinit var tvEventsContent: TextView
    private lateinit var scrollEvents: ScrollView // Cambio aquí a ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)

        // Vínculo de vistas
        ivProfilePicture = findViewById(R.id.iv_profile_picture)
        tvUserName = findViewById(R.id.tv_user_name)
        etMessage = findViewById(R.id.et_message)
        btnInstagram = findViewById(R.id.btn_instagram)
        btnFacebook = findViewById(R.id.btn_facebook)
        tvEventsContent = findViewById(R.id.tv_events_content)
        scrollEvents = findViewById(R.id.scroll_events) // Cambio aquí a ScrollView

        // Configuración de la imagen de perfil (puedes hacerla dinámica con URL)
        ivProfilePicture.setImageResource(R.drawable.ic_person) // Usar una imagen predeterminada

        // Configuración del nombre de usuario
        tvUserName.text = "Nombre de Usuario" // Este nombre puede venir de la base de datos o de una variable

        // Mostrar eventos históricos relevantes (puedes hacer esto dinámicamente, aquí es estático)
        tvEventsContent.text = "Evento 1: Algo importante sucedió en esta fecha...\nEvento 2: Otro evento relevante...\nEvento 3: Más historia aquí..."

        // Acción para el botón de Instagram
        btnInstagram.setOnClickListener {
            val message = etMessage.text.toString()
            if (message.isNotEmpty()) {
                val instagramIntent = Intent(Intent.ACTION_SEND)
                instagramIntent.type = "text/plain"
                instagramIntent.putExtra(Intent.EXTRA_TEXT, message)
                instagramIntent.setPackage("com.instagram.android") // Aseguramos que se abra Instagram
                try {
                    startActivity(instagramIntent)
                } catch (e: Exception) {
                    Toast.makeText(this, "Instagram no está instalado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, escribe un mensaje", Toast.LENGTH_SHORT).show()
            }
        }

            // Acción para el botón de Facebook
        btnFacebook.setOnClickListener {
            val message = etMessage.text.toString()
            if (message.isNotEmpty()) {
                val facebookIntent = Intent(Intent.ACTION_SEND)
                facebookIntent.type = "text/plain"
                facebookIntent.putExtra(Intent.EXTRA_TEXT, message)
                facebookIntent.setPackage("com.facebook.orca") // Aseguramos que se abra Facebook
                try {
                    startActivity(facebookIntent)
                } catch (e: Exception) {
                    Toast.makeText(this, "Messenger no está instalado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, escribe un mensaje", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
