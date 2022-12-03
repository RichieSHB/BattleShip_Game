package com.example.battleship_game

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RoomAdapter(private val registros: List<Room>):RecyclerView.Adapter<RoomAdapter.RoomsHolder>() {
    class RoomsHolder(val view: View): RecyclerView.ViewHolder(view){



        fun render(rooms: Room){
            val btnJoin: Button = view.findViewById(R.id.btnJoinRoom)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RoomsHolder(layoutInflater.inflate(R.layout.room_item,parent,false))
    }

    override fun onBindViewHolder(holder: RoomsHolder, position: Int) {
        holder.render(registros[position])
    }

    override fun getItemCount(): Int {
        return registros.size
    }
}

