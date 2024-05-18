package com.example.dreamwingsiv

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DropDownList : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drop_down_list)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        database = FirebaseDatabase.getInstance().reference

        val place = resources.getStringArray(R.array.Places)
        val where = resources.getStringArray(R.array.Places)
        val jack = resources.getStringArray(R.array.Peopleclass)
        val rox = resources.getStringArray(R.array.people)

        val spin = findViewById<Spinner>(R.id.spin1)
        val snow = findViewById<Spinner>(R.id.spin2)
        val jow = findViewById<Spinner>(R.id.spin3)
        val now = findViewById<Spinner>(R.id.spin4)

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, place)
        spin.adapter = arrayAdapter
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedPlace = place[position]
                saveDataToDatabase("selectedPlace", selectedPlace)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (optional)
            }
        }

        val jam = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, where)
        snow.adapter = jam
        snow.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedWhere = where[position]
                saveDataToDatabase("currentLocation", selectedWhere)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (optional)
            }
        }

        val jake = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, jack)
        jow.adapter = jake
        jow.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedJack = jack[position]
                saveDataToDatabase("Position In Plane", selectedJack)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (optional)
            }
        }

        val dog = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rox)
        now.adapter = dog
        now.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedRox = rox[position]
                saveDataToDatabase("Passenger", selectedRox)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (optional)
            }
        }
    }

    private fun saveDataToDatabase(key: String, value: String) {
        val bookingsRef = database.child("Book").child(key)
        bookingsRef.setValue(value)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Successfully Booked Flight", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Failed to book Flight", Toast.LENGTH_LONG).show()
            }
    }
}