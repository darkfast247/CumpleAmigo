package com.example.relojdevida

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageButton
import android.util.Log
import android.content.Intent
import android.content.Context

// Asegúrate de pasar el contexto al adaptador
class MyAdapter(private var items: List<String>, private val context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageButton: ImageButton = itemView.findViewById(R.id.imageButton)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = items[position]
        // Separar la cadena usando el delimitador '|'
        val parts = currentItem.split("|")
        val name = parts.getOrNull(0) ?: "Nombre no disponible"
        val birthDate = parts.getOrNull(1) ?: "Fecha no disponible"
        val socialLink = parts.getOrNull(2) ?: "Enlace no disponible"

        // Mostrar los datos en el TextView
        holder.textView.text = "$name - $birthDate"
        // Agregar el listener para el ImageButton
        holder.imageButton.setOnClickListener {
            try {
                // Asegúrate de que 'context' sea válido y la actividad esté declarada
                val intent = Intent(context, EnviarMensajeActivity::class.java)
                // Enviar datos al Intent (nombre del usuario y el enlace de redes sociales)
                intent.putExtra("USER_NAME", name)
                intent.putExtra("SOCIAL_LINK", socialLink)
                context.startActivity(intent)
            } catch (e: Exception) {
                // Si hay un error, lo mostramos en Logcat
                Log.e("MyAdapter", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }


    override fun getItemCount() = items.size

    // Método para actualizar la lista
    fun updateItems(newItems: List<String>) {
        items = newItems
        notifyDataSetChanged()
    }
}