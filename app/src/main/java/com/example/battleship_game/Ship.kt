package com.example.battleship_game

import java.text.FieldPosition

class Ships() {
    var shipPositionY: Float = 0.0f
    var shipPositionX: Float = 0.0f
    var shipSelected:Int = 1
    //0 vertical, 1 horizontal
    var shipOrientation:Int = 0
    var shipSizeFloat:Float = 0F
    var shipSize:Int = 0
    var isCreated:Boolean = false

    fun shipSelected():Int{
        return shipSelected
    }

    fun selectShip(ship:Int){
        shipSelected = ship
    }

    fun shipSizeFloat():Float{
        when(shipSelected){
            1 -> shipSizeFloat = 100F
            2 -> shipSizeFloat = 200F
            3 -> shipSizeFloat = 300F
            4 -> shipSizeFloat = 400F
            5 -> shipSizeFloat = 500F
        }
        return shipSizeFloat
    }

    fun shipSize():Int{
        when(shipSelected){
            1 -> shipSize = 1
            2 -> shipSize = 2
            3 -> shipSize = 3
            4 -> shipSize = 4
            5 -> shipSize = 5
        }
        return shipSize
    }

}