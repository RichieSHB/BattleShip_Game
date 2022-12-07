package com.example.battleship_game

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GameActivity : AppCompatActivity() {

    var isHostTurn: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    fun endGame(){
        var shootEnemy = ShootEnemy()
        var shoot = Shoot()
        shootEnemy.resetList()
        shoot.resetList()
        var board = Board()
        board.clearBoard()
        AlertDialog.Builder(this)
            .setTitle("Ganador!!!!!")
            .setMessage("Ganaste precioso <3")
            .setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }).show()
    }

    fun changeTurn() {
        isHostTurn = !isHostTurn
       val intent = Intent(this, BoardActivity::class.java)
        intent.putExtra("isHostTurn", isHostTurn)
        startActivity(intent)
    }

}