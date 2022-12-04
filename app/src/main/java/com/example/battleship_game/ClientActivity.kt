package com.example.battleship_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ClientActivity : AppCompatActivity() {

    lateinit var btnJoin: Button
    lateinit var btnReady: Button
    lateinit var tvPlayer1: TextView
    lateinit var tvPlayer2: TextView
    lateinit var tvPlayer1Ready: TextView
    lateinit var tvPlayer2Ready: TextView
    lateinit var RoomName: TextView
    lateinit var etUsername: TextView

    val FirebaseDb = FirebaseDB_Provider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        var isReady = false

        val extras: Intent = intent
        val roomName = extras.getStringExtra("roomId")
        val modRoom = FirebaseDb.firebase.getReference("rooms").child(roomName.toString())

        if (roomName != null) {
            RoomName = findViewById(R.id.tvRoomName)
            RoomName.text = "no se encontro Sala"
        }

        btnJoin = findViewById(R.id.btnJoin)
        btnReady = findViewById(R.id.btnReady)
        tvPlayer1 = findViewById(R.id.tvPlayer1)
        tvPlayer2 = findViewById(R.id.tvPlayer2)
        tvPlayer1Ready = findViewById(R.id.tvReadyPlayer1)
        tvPlayer2Ready = findViewById(R.id.tvReadyPlayer2)
        RoomName = findViewById(R.id.tvRoomName)
        etUsername = findViewById(R.id.etUsername)

        RoomName.text = "Sala: $roomName"

        btnJoin.setOnClickListener {
            tvPlayer2.text = etUsername.text
            btnJoin.visibility = Button.INVISIBLE
            etUsername.visibility = TextView.INVISIBLE

            btnReady.visibility = Button.VISIBLE
            tvPlayer1.visibility = TextView.VISIBLE
            tvPlayer2.visibility = TextView.VISIBLE
            tvPlayer1Ready.visibility = TextView.VISIBLE
            tvPlayer2Ready.visibility = TextView.VISIBLE

            modRoom.child("player2").setValue(etUsername.text.toString())
        }

        btnReady.setOnClickListener {
            if (isReady) {
                tvPlayer1Ready.text = "No Listo"
                isReady = false
                modRoom.child("player2Ready").setValue(isReady)
            } else {
                tvPlayer1Ready.text = "Listo"
                isReady = true
                modRoom.child("player2Ready").setValue(isReady)

            }

            modRoom.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val room = snapshot.getValue(Room::class.java)
                    if (room != null) {
                        tvPlayer1.text = room.player1
                        if (room.player1Ready) {
                            tvPlayer1Ready.text = "Listo"
                        } else {
                            tvPlayer1Ready.text = "No Listo"
                        }
                        if (room.player2Ready) {
                            tvPlayer2Ready.text = "Listo"
                        } else {
                            tvPlayer2Ready.text = "No Listo"
                        }
                        if (room.player1Ready && room.player2Ready) {
                            val intent = Intent(this@ClientActivity, BoardActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        }
    }
}