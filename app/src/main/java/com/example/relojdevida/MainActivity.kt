package com.example.relojdevida

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // CODIGO PARA LA OTRA PANTALLA NO TOCAR
        val btn : com.google.android.material.floatingactionbutton.FloatingActionButton  = findViewById(R.id.fab_add)
        btn.setOnClickListener {
            val  intent : Intent = Intent(this, agregar_cumple :: class.java)
            startActivity(intent)
        }

        


        val mainLayout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)

        // Configura el RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Lista de ejemplo para probar
        val items = listOf("Elemento 1", "Elemento 2", "Elemento 3")

        // Asigna el adaptador al RecyclerView
        recyclerView.adapter = MyAdapter(items)

        // Ajuste de padding para ventanas de insets
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                left = systemBarsInsets.left,
                top = systemBarsInsets.top,
                right = systemBarsInsets.right,
                bottom = systemBarsInsets.bottom
            )
            insets
        }
    }
}
