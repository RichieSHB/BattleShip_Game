package com.example.battleship_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnPvP = findViewById<Button>(R.id.btnPvP)

        btnPvP.setOnClickListener {
            val intent = Intent(this,BoardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}