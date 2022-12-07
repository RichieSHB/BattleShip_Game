package com.example.battleship_game

import android.graphics.Rect

class ShootEnemy {
    var hit: Boolean = false
    lateinit var Rect: Rect

    fun addShoot(rect: Rect, hit: Boolean) {
        var shoot =  Shoot()
        shoot.Rect = rect
        shoot.hit = hit
        shots.add(shoot)
    }

    fun getShots(): MutableList<Shoot> {
        return shots
    }

    fun puntuacion(): Int {
        var puntuacion = 0
        for (i in 0..Shoot.shots.size) {
            if (Shoot.shots[i].hit) {
                puntuacion += 1
            }
        }
        return puntuacion
    }

    fun resetList() {
        shots.clear()
    }

    companion object{
        var shots: MutableList<Shoot> = mutableListOf()
    }
}