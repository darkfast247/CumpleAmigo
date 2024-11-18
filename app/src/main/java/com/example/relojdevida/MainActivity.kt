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
import android.widget.ImageButton


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

        val items = listOf("Item 1", "Item 2", "Item 3") // Ejemplo de lista
        val adapter = MyAdapter(items, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

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
