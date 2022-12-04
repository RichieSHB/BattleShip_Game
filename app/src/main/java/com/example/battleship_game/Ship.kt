package com.example.battleship_game

class Ships( var shipSize: Float,  var shipWidth: Float,  var shipHeight: Float,var shipSelected:Int) {

    fun shipSelected():Int{
        return shipSelected
    }

    fun selectShip(ship:Int){
        shipSelected = ship
    }

    fun shipSize():Float{
        when(shipSelected){
            0 -> shipSize = 100F
            1 -> shipSize = 200F
            2 -> shipSize = 300F
            3 -> shipSize = 400F
            4 -> shipSize = 500F
        }
        return shipSize
    }
}