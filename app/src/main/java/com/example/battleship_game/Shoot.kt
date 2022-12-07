package com.example.battleship_game

import android.graphics.Rect

class Shoot {
       var hit: Boolean = false
       lateinit var Rect: Rect
       var puntuacion: Int = 0

       fun addShoot(rect: Rect, hit: Boolean) {
           var shoot =  Shoot()
              shoot.Rect = rect
                shoot.hit = hit
              shots.add(shoot)
       }

    fun getShots(): MutableList<Shoot> {
              return shots
         }

    fun puntuacion(): Boolean {
        for (i in shots.size-1 downTo 0) {
            if (shots[i].hit) {
                puntuacion += 1
            }
        }
        return puntuacion == 15
    }

    fun resetList() {
        shots.clear()
    }

       companion object{
              var shots: MutableList<Shoot> = mutableListOf()
       }
}