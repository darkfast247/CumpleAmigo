package com.example.relojdevida

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.*
import com.google.ai.client.generativeai.GenerativeModel

class EnviarMensajeActivity : AppCompatActivity() {

    // Inicialización de las vistas
    private lateinit var ivProfilePicture: ImageView
    private lateinit var tvUserName: TextView
    private lateinit var etMessage: EditText
    private lateinit var btnInstagram: Button
    private lateinit var btnFacebook: Button
    private lateinit var tvEventsContent: TextView
    private lateinit var scrollEvents: ScrollView

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
        scrollEvents = findViewById(R.id.scroll_events)

        ivProfilePicture.setImageResource(R.drawable.ic_person)

        // Recuperar los datos del Intent
        val userName = intent.getStringExtra("USER_NAME")
        val socialLink = intent.getStringExtra("SOCIAL_LINK")

        // Mostrar los datos en los TextViews
        tvUserName.text = userName ?: "Nombre no disponible"


        // Configuración de los botones de Instagram y Facebook
        btnInstagram.setOnClickListener {
            val message = etMessage.text.toString()
            if (message.isNotEmpty()) {
                val instagramIntent = Intent(Intent.ACTION_SEND)
                instagramIntent.type = "text/plain"
                instagramIntent.putExtra(Intent.EXTRA_TEXT, message)
                instagramIntent.setPackage("com.instagram.android")
                try {
                    startActivity(instagramIntent)
                } catch (e: Exception) {
                    Toast.makeText(this, "Instagram no está instalado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, escribe un mensaje", Toast.LENGTH_SHORT).show()
            }
        }

        btnFacebook.setOnClickListener {
            val message = etMessage.text.toString()
            if (message.isNotEmpty()) {
                val facebookIntent = Intent(Intent.ACTION_SEND)
                facebookIntent.type = "text/plain"
                facebookIntent.putExtra(Intent.EXTRA_TEXT, message)
                facebookIntent.setPackage("com.facebook.orca")
                try {
                    startActivity(facebookIntent)
                } catch (e: Exception) {
                    Toast.makeText(this, "Messenger no está instalado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, escribe un mensaje", Toast.LENGTH_SHORT).show()
            }
        }

        // Llamada a la API para obtener los eventos históricos
        getHistoricalEvents()
    }

    private fun getHistoricalEvents() {
        val currentDate = SimpleDateFormat("dd-MM", Locale.getDefault()).format(Date())

        // Realizamos la solicitud a la API de Gemini usando Retrofit
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Aquí puedes integrar la API de Gemini para obtener los eventos históricos
                val apiKey = "AIzaSyB7f2090OS2J-SnC8gYME0Jw_e6FeDzjMk"

                val generativeModel = GenerativeModel(
                    modelName = "gemini-1.5-flash",
                    apiKey = apiKey
                )


                // Generar el prompt para la consulta de eventos históricos
                val prompt = """
    Proporciona una lista de eventos o noticias que ocurrieron en el día de hoy, en cualquier año de la historia, sin importar su relevancia o tipo de evento (pueden ser eventos históricos, deportes, cultura pop, tecnología, etc.).
    No es necesario que sean muy conocidos, solo que hayan sucedido en esta fecha exacta (solo día y mes, se requiere el año).
    
    **Formato requerido**:
    - **Fecha**: Solo día y mes.
    - **Título del evento/noticia**: Un título claro y breve del evento o noticia.
    - **Descripción**: Una breve descripción del evento o noticia.

    El día de hoy es: $currentDate.
"""





                // Generar la respuesta de los eventos
                val response = generativeModel.generateContent(prompt)

                withContext(Dispatchers.Main) {
                    if (!response.text.isNullOrEmpty()) {
                        // Si la respuesta no es null ni está vacía, muestra los eventos
                        tvEventsContent.text = "Eventos del día:\n\n${response.text}"
                    } else {
                        // Si la respuesta es null o está vacía, mostrar mensaje por defecto
                        tvEventsContent.text = "No se encontraron eventos históricos."
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Manejo de error si no se pueden obtener los eventos
                    Toast.makeText(this@EnviarMensajeActivity, "Error al obtener eventos: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.e("API_ERROR", "Error al obtener eventos: ${e.message}", e)
                }
            }
        }
    }
}
