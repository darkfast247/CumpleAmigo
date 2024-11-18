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
class MyAdapter(private val items: List<String>, private val context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

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
        holder.textView.text = items[position]

        holder.imageButton.setOnClickListener {
            try {
                // Asegúrate de que 'context' sea válido y la actividad esté declarada
                val intent = Intent(context, EnviarMensajeActivity::class.java)
                context.startActivity(intent)
            } catch (e: Exception) {
                // Si hay un error, lo mostramos en Logcat
                Log.e("MyAdapter", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount() = items.size
}