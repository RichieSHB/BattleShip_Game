package com.example.battleship_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class BoardActivity : AppCompatActivity() {

    lateinit var spinner: Spinner
    lateinit var btnOrientacion: Button
    lateinit var btnReady: Button
     var shipSelected: Int = 0
    var orientacion: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        val ships = arrayOf("Lancha", "Destructor", "Submarino", "Crucero", "Portaaviones")

        spinner = findViewById(R.id.spinner)
        btnOrientacion = findViewById(R.id.btnOrientacion)
        btnReady = findViewById(R.id.btnReady)

        btnOrientacion.setOnClickListener {
            if (btnOrientacion.text == "Horizontal") {
                btnOrientacion.text = "Vertical"
                orientacion = 1
            } else {
                btnOrientacion.text = "Horizontal"
                orientacion = 0
            }
        }

        btnReady.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
            finish()
        }

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ships)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(ships[position]){
                    "Lancha" -> shipSelected = 0
                    "Destructor" -> shipSelected = 3
                    "Submarino" -> shipSelected = 2
                    "Crucero" -> shipSelected = 4
                    "Portaaviones" -> shipSelected = 1
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }


}


