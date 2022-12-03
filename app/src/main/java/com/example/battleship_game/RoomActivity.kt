package com.example.battleship_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class RoomActivity : AppCompatActivity() {

    lateinit var btnCreateRoom: Button
    lateinit var btnSearch:ImageButton
    lateinit var edSearch:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        btnCreateRoom = findViewById(R.id.btnCreateRoom)

        btnCreateRoom.setOnClickListener {
            val intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
        }
    }
}