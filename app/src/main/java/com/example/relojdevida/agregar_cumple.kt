package com.example.relojdevida
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class agregar_cumple : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agregar_cumple)


        // Referencias a los campos de texto y el botón
        val etName = findViewById<EditText>(R.id.etName)
        val etSocial = findViewById<EditText>(R.id.etSocial)
        val etDate = findViewById<EditText>(R.id.Fecha)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        // Configuración del selector de fecha
        etDate.setOnClickListener {
            showDatePickerDialog(etDate)
        }

        // Acción del botón "Agregar" para el Myadapter
        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val socialLink = etSocial.text.toString()
            val birthDate = etDate.text.toString()
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("nombre", name)
            intent.putExtra("redesSociales", socialLink)
            intent.putExtra("fecha", birthDate)
            startActivity(intent)

            if (name.isNotEmpty() && socialLink.isNotEmpty() && birthDate.isNotEmpty()) {
                Toast.makeText(this, "Cumpleaños agregado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para mostrar el selector de fecha
    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
            editText.setText(formattedDate)
        }, year, month, day)

        datePickerDialog.show()


    }
}

