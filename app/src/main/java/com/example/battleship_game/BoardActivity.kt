package com.example.battleship_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.battleship_game.Board.Companion.board

class BoardActivity : AppCompatActivity() {

    lateinit var spinner: Spinner
    lateinit var btnOrientacion: Button
    lateinit var btnReady: Button
     var shipSelected: Int = 0
    var orientacion: Int = 0
    var board: Board = Board()
    var isGamebegin: Boolean = false
    var isHostTurn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        val extras: Intent = intent
         isHostTurn = extras.getBooleanExtra("isHostTurn", true)

        val ships = arrayOf("Lancha", "Destructor", "Submarino", "Crucero", "Portaaviones")

        val BoardView = findViewById<View>(R.id.boardView)

        spinner = findViewById(R.id.spinner)
        btnOrientacion = findViewById(R.id.btnOrientacion)
        btnReady = findViewById(R.id.btnPlay)

        btnOrientacion.setOnClickListener {
            if (btnOrientacion.text == "Horizontal") {
                btnOrientacion.text = "Vertical"
                orientacion = 1
            } else {
                btnOrientacion.text = "Horizontal"
                orientacion = 0
            }
        }

        if (!isHostTurn){
            spinner.visibility = View.INVISIBLE
            btnOrientacion.visibility = View.INVISIBLE
            btnReady.visibility = View.INVISIBLE
        }


        btnReady.setOnClickListener {

            if (board.allShips()) {
                spinner.visibility = View.INVISIBLE
                btnOrientacion.visibility = View.INVISIBLE
                isGamebegin = true
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Aun no has colocado todos los barcos", Toast.LENGTH_SHORT).show()
            }
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

    fun changeTurn() {
        isHostTurn = !isHostTurn
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("isHostTurn", isHostTurn)
        startActivity(intent)
    }
}


