package com.example.relojdevida

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa el RecyclerView y el adaptador
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = MyAdapter(mutableListOf(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar los cumpleaños desde SharedPreferences
        val sharedPreferences = getSharedPreferences("birthdayPrefs", MODE_PRIVATE)
        val savedBirthdays = sharedPreferences.getStringSet("birthdays", mutableSetOf()) ?: mutableSetOf()

        // Convertir la lista de cumpleaños a un formato adecuado para el RecyclerView
        val items = savedBirthdays.toList()

        // Actualizar el adaptador con los elementos cargados
        adapter.updateItems(items)

        // Configura el botón flotante para agregar un cumpleaños
        val btn: com.google.android.material.floatingactionbutton.FloatingActionButton = findViewById(R.id.fab_add)
        btn.setOnClickListener {
            val intent = Intent(this, AgregarCumple::class.java)
            startActivity(intent)
        }
    }

    // Puedes actualizar los elementos desde la pantalla de agregar cumpleaños (cuando se regresa a MainActivity)
    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("birthdayPrefs", MODE_PRIVATE)
        val savedBirthdays = sharedPreferences.getStringSet("birthdays", mutableSetOf()) ?: mutableSetOf()
        val items = savedBirthdays.toList()
        adapter.updateItems(items)
    }
}


// CODIGO PARA LA OTRA PANTALLA NO TOCAR

