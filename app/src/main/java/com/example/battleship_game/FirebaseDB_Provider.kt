package com.example.battleship_game

import com.google.firebase.database.ktx.database

class FirebaseDB_Provider() {
    val firebase = com.google.firebase.ktx.Firebase.database

    fun createRoom(roomInfo: Room,id:String) {
        val room = firebase.getReference("rooms")
        room.child(id).setValue(roomInfo)
    }

}