package com.example.battleship_game

import android.util.Log

class Board {

   fun dropShip(ship: Ships){
        for (i in 0..7){
            for (j in 0..7){
                if (board[i][j] == ship.shipSelected){
                    board[i][j] = 0
                }
            }
        }
    }

    fun  getPuntuacion(): Int{
        return puntuacion
    }

    fun pushShip(ship:Ships):Boolean{
        val shipSize = ship.shipSize()
        val shipOrientation = ship.shipOrientation
        val shipPositionX = ship.shipPositionX
        val shipPositionY = ship.shipPositionY
        val shipSelected = ship.shipSelected()
        var xOrigin = 0
        var yOrigin = 0
        var xOriginC = 0
        var yOriginC = 0

        when (true){
            (shipPositionX >= 150f &&  shipPositionX < 250f) ->  xOrigin = 0
            (shipPositionX >= 250f &&  shipPositionX < 350f) ->  xOrigin = 1
            (shipPositionX >=350f &&  shipPositionX < 450f) ->  xOrigin = 2
            (shipPositionX >= 450f &&  shipPositionX < 550f) ->  xOrigin = 3
            (shipPositionX >= 550f &&  shipPositionX < 650f) ->  xOrigin = 4
            (shipPositionX >= 650f &&  shipPositionX < 750f) ->  xOrigin = 5
            (shipPositionX >= 750f &&  shipPositionX < 850f) ->  xOrigin = 6
            (shipPositionX >= 850f &&  shipPositionX < 950f) ->  xOrigin = 7
            else -> {}
        }
        when (true){
            (shipPositionY == 200f ) ->  yOrigin = 0
            (shipPositionY == 300f ) ->  yOrigin = 1
            (shipPositionY == 400f ) ->  yOrigin = 2
            (shipPositionY == 500f ) ->  yOrigin = 3
            (shipPositionY == 600f ) ->  yOrigin = 4
            (shipPositionY == 700f ) ->  yOrigin = 5
            (shipPositionY == 800f ) ->  yOrigin = 6
            (shipPositionY == 900f ) ->  yOrigin = 7
            else -> {}
        }

        xOriginC = xOrigin
        yOriginC = yOrigin

        Log.d("Board","orientation: $shipOrientation")

        if (shipOrientation == 0) {
            for (i in 0 until shipSize) {
                if (board[xOriginC][yOriginC] != 0) {
                        return false
                } else {
                    Log.d("Board", "pushShip: $xOriginC $yOriginC")
                    Log.d("Board", "[${xOriginC}][${yOriginC}]")
                }
                xOriginC++
                if (xOriginC > 7) {
                    return false
                }
            }
        }else{
            for (i in 0 until shipSize) {
                if (board[xOrigin][yOrigin] != 0) {
                    return false
                } else {
                    Log.d("Board", "pushShip: $xOrigin $yOrigin")
                    Log.d("Board", "[${xOrigin}][${yOrigin}]")
                }
                yOriginC++
                if (yOriginC > 7) {
                    return false
                }
            }
        }
        if(shipOrientation == 0){
            for(i in 0 until shipSize){
                board[xOrigin][yOrigin] = shipSelected
                xOrigin++
                if (xOrigin > 7){
                    dropShip(ship)
                    return false
                }
            }
        }else{
            for(i in 0 until shipSize){
                board[xOrigin][yOrigin] = shipSelected
                yOrigin++
                if (yOrigin > 7){
                    dropShip(ship)
                    return false
                }
            }
        }
      var matriz = ""
        for (i in 0 until 8){
            for (j in 0 until 8){
                matriz += "${board[i][j]} "

            }
            Log.d("Board", "$matriz")
            matriz = ""
        }

        return true
    }

    fun shootShip(shipPositionX:Float,shipPositionY:Float):Boolean{
        var xOrigin = 0
        var yOrigin = 0
        when (true){
            (shipPositionX >= 150f &&  shipPositionX < 250f) ->  xOrigin = 0
            (shipPositionX >= 250f &&  shipPositionX < 350f) ->  xOrigin = 1
            (shipPositionX >=350f &&  shipPositionX < 450f) ->  xOrigin = 2
            (shipPositionX >= 450f &&  shipPositionX < 550f) ->  xOrigin = 3
            (shipPositionX >= 550f &&  shipPositionX < 650f) ->  xOrigin = 4
            (shipPositionX >= 650f &&  shipPositionX < 750f) ->  xOrigin = 5
            (shipPositionX >= 750f &&  shipPositionX < 850f) ->  xOrigin = 6
            (shipPositionX >= 850f &&  shipPositionX < 950f) ->  xOrigin = 7
            else -> {}
        }
        when (true){
            (shipPositionY == 200f ) ->  yOrigin = 0
            (shipPositionY == 300f ) ->  yOrigin = 1
            (shipPositionY == 400f ) ->  yOrigin = 2
            (shipPositionY == 500f ) ->  yOrigin = 3
            (shipPositionY == 600f ) ->  yOrigin = 4
            (shipPositionY == 700f ) ->  yOrigin = 5
            (shipPositionY == 800f ) ->  yOrigin = 6
            (shipPositionY == 900f ) ->  yOrigin = 7
            else -> {}
        }
        if (board[xOrigin][yOrigin] != 0){
            puntuacion+=10
            return true
        }
        return false
    }

    fun allShips():Boolean{
        var shipSize:Int = 0
        board.find { it.contains(1) }?.let { shipSize++ }
        board.find { it.contains(2) }?.let { shipSize++ }
        board.find { it.contains(3) }?.let { shipSize++ }
        board.find { it.contains(4) }?.let { shipSize++ }
        board.find { it.contains(5) }?.let { shipSize++ }
        return shipSize == 5
    }

    fun clearBoard(){
        for (i in 0 until 8){
            for (j in 0 until 8){
                board[i][j] = 0
            }
        }
    }

    companion object{
        var board: Array<Array<Int>> = Array(8) { Array(8) { 0 } }
        var puntuacion = 0
    }
}