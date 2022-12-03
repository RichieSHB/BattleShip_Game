package com.example.battleship_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
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

        btnCrear = findViewById(R.id.btnCrear)
        btnReady = findViewById(R.id.btnReady)
        tvPlayer1 = findViewById(R.id.tvPlayer1)
        tvPlayer2 = findViewById(R.id.tvPlayer2)
        tvPlayer1Ready = findViewById(R.id.tvReadyPlayer1)
        tvPlayer2Ready = findViewById(R.id.tvReadyPlayer2)
        RoomName = findViewById(R.id.tvRoomName)
        etUsername = findViewById(R.id.etUsername)

        val modRoom = FirebaseDb.firebase.getReference("rooms")
        var roomId = Random.nextInt(1, 999)
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
               roomInfo.player1Ready = false
                modRoom.child(roomId.toString()).setValue(roomInfo)
           } else {
               tvPlayer1Ready.text = "Listo"
               isReady = true
               roomInfo.player1Ready = true
               modRoom.child(roomId.toString()).setValue(roomInfo)
           }
        }

        modRoom.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    val room = child.getValue(Room::class.java)
                    if (room?.id == roomId.toString()) {
                        tvPlayer2.text = room.player2
                        if (room.player2Ready) {
                            tvPlayer2Ready.text = "Listo"
                        } else {
                            tvPlayer2Ready.text = "No Listo"
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
