package com.example.battleship_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RoomActivity : AppCompatActivity() {

    lateinit var btnCreateRoom: Button
    lateinit var btnSearch:ImageButton
    lateinit var edSearch:EditText
    lateinit var rvSalas: RecyclerView

    lateinit var rooms: List<Room>

    lateinit var DbProvider: FirebaseDB_Provider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        DbProvider = FirebaseDB_Provider()
        btnCreateRoom = findViewById(R.id.btnCreateRoom)
        btnSearch = findViewById(R.id.btnSearch)
        edSearch = findViewById(R.id.etRoomSearch)

        btnCreateRoom.setOnClickListener {
            val intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
        }

        btnSearch.setOnClickListener {
            val intent = Intent(this, ClientActivity::class.java)
            intent.putExtra("roomId", edSearch.text.toString())
            startActivity(intent)
        }
    }


}