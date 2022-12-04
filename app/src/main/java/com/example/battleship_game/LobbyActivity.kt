package com.example.battleship_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlin.random.Random

class LobbyActivity : AppCompatActivity() {

    lateinit var btnCrear: Button
    lateinit var btnReady: Button
    lateinit var tvPlayer1: TextView
    lateinit var tvPlayer2: TextView
    lateinit var tvPlayer1Ready: TextView
    lateinit var tvPlayer2Ready: TextView
    lateinit var RoomName: TextView
    lateinit var etUsername:EditText
    val FirebaseDb = FirebaseDB_Provider()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)

        var isReady = false

        btnCrear = findViewById(R.id.btnJoin)
        btnReady = findViewById(R.id.btnReady)
        tvPlayer1 = findViewById(R.id.tvPlayer1)
        tvPlayer2 = findViewById(R.id.tvPlayer2)
        tvPlayer1Ready = findViewById(R.id.tvReadyPlayer1)
        tvPlayer2Ready = findViewById(R.id.tvReadyPlayer2)
        RoomName = findViewById(R.id.tvRoomName)
        etUsername = findViewById(R.id.etUsername)

        var roomId = Random.nextInt(1, 999)
        val modRoom = FirebaseDb.firebase.getReference("rooms").child(roomId.toString())
        var roomInfo = Room(roomId.toString(), "Sin Host","Sin rival",false,false)

        RoomName.text = "Sala: $roomId"

        btnCrear.setOnClickListener {

            tvPlayer1.text = etUsername.text
            roomInfo.player1 = etUsername.text.toString()
            FirebaseDb.createRoom(roomInfo, roomId.toString())

            btnCrear.visibility = Button.INVISIBLE
            etUsername.visibility = EditText.INVISIBLE

            btnReady.visibility = Button.VISIBLE
            tvPlayer1Ready.visibility = TextView.VISIBLE
            tvPlayer2Ready.visibility = TextView.VISIBLE
            tvPlayer1.visibility = TextView.VISIBLE
            tvPlayer2.visibility = TextView.VISIBLE
        }

        btnReady.setOnClickListener {
           if (isReady) {
               tvPlayer1Ready.text = "No Listo"
               isReady = false
                modRoom.child("player1Ready").setValue(isReady)
           } else {
               tvPlayer1Ready.text = "Listo"
               isReady = true
               modRoom.child("player1Ready").setValue(isReady)

           }
        }

       modRoom.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val room = snapshot.getValue(Room::class.java)
                    tvPlayer2.text = room?.player2
                    if (room != null) {
                        tvPlayer1.text = room.player1
                        if (room.player1Ready) {
                            tvPlayer1Ready.text = "Listo"
                        }else{
                            tvPlayer1Ready.text = "No Listo"
                        }
                        if (room.player2Ready) {
                            tvPlayer2Ready.text = "Listo"
                        } else {
                            tvPlayer2Ready.text = "No Listo"
                        }
                        if (room.player1Ready && room.player2Ready) {
                            val intent = Intent(this@LobbyActivity, BoardActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        }
    }
