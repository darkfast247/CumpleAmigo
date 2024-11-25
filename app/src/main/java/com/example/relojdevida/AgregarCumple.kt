package com.example.relojdevida

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson



class AgregarCumple : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agregar_cumple)

        // Referencias a los campos de texto y el botón
        val etName = findViewById<EditText>(R.id.etName)
        val etSocial = findViewById<EditText>(R.id.etSocial)
        val etDate = findViewById<EditText>(R.id.Fecha)
        val btnAdd = findViewById<Button>(R.id.btnAdd)



        // Cargar cumpleaños guardados desde SharedPreferences
        val storedBirthdays = loadBirthdaysFromPreferences()
        // Aquí puedes hacer lo que necesites con los cumpleaños cargados, como mostrarlos en una lista
        println("Cumpleaños guardados: $storedBirthdays")

        // Configuración del selector de fecha
        etDate.setOnClickListener {
            showDatePickerDialog(etDate)
        }


        // Acción del botón "Agregar"
        btnAdd.setOnClickListener {
            val name = etName.text.toString().trim()
            val socialLink = etSocial.text.toString().trim()
            val birthDate = etDate.text.toString().trim()

            if (name.isEmpty() || birthDate.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos obligatorios.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Agregar el cumpleaños al Singleton
            addBirthdayToSingleton(name, birthDate, socialLink)

            // Guardar en SharedPreferences
            saveBirthdayToPreferences(name, birthDate, socialLink)

            // Limpiar los campos de texto
            etName.text.clear()
            etSocial.text.clear()
            etDate.text.clear()
        }
    }

    // Función para agregar cumpleaños al Singleton
    private fun addBirthdayToSingleton(name: String, birthDate: String, socialLink: String) {
        DataManager.birthdayList.add(DataManager.Birthday(name, birthDate, socialLink))
        Toast.makeText(this, "Cumpleaños agregado exitosamente a la lista.", Toast.LENGTH_SHORT).show()

        // Si quieres, también puedes guardar en MongoDB o SharedPreferences aquí
    }

    private fun saveBirthdayToPreferences(name: String, birthDate: String, socialLink: String) {
        val sharedPreferences = getSharedPreferences("birthdayPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Crear una representación en formato cadena separada por '|'
        val birthdayData = "$name|$birthDate|$socialLink"

        // Recuperar los cumpleaños existentes y agregar el nuevo
        val existingData = sharedPreferences.getStringSet("birthdays", mutableSetOf()) ?: mutableSetOf()
        existingData.add(birthdayData)

        // Guardar los datos en SharedPreferences
        editor.putStringSet("birthdays", existingData)
        editor.apply()

        Toast.makeText(this, "Cumpleaños guardado.", Toast.LENGTH_SHORT).show()
    }

    private fun loadBirthdaysFromPreferences(): List<String> {
        val sharedPreferences = getSharedPreferences("birthdayPrefs", MODE_PRIVATE)
        val birthdays = mutableListOf<String>()

        // Recuperar todos los cumpleaños guardados
        sharedPreferences.getStringSet("birthdays", mutableSetOf())?.forEach { entry ->
            birthdays.add(entry)  // Aquí ya puedes separar la cadena si lo deseas
        }

        // Mostrar el contenido en Logcat para inspección
        Log.d("Birthdays", "Cumpleaños guardados: $birthdays")

        return birthdays
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
