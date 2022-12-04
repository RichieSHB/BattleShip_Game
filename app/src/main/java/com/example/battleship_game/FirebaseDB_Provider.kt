package com.example.battleship_game

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database


class FirebaseDB_Provider() {
    val firebase = com.google.firebase.ktx.Firebase.database

    fun createRoom(roomInfo: Room,id:String) {
        val room = firebase.getReference("rooms")
        room.child(id).setValue(roomInfo)
    }

    fun getRooms(): List<Room> {
        val room = firebase.getReference("rooms")
        val rooms: MutableList<Room> = mutableListOf()
        print("hola")
        room.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val t: GenericTypeIndicator<Room?> =
                    object : GenericTypeIndicator<Room?>() {}
                for (room in snapshot.children) {
                    print(room)
                    val roomInfo = room.getValue(t)
                    if (roomInfo != null) {
                        rooms.add(roomInfo)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        })
        return rooms
    }

}