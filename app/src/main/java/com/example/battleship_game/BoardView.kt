package com.example.battleship_game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.util.*

class BoardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
   private val BoardActivity = context as BoardActivity

    private final val cellside: Float = 100f
    private final val originX: Float = 150f
    private final val originY: Float = 200f

    private var drawShipLancha: Boolean = false
    private var drawShipPortaAviones: Boolean = false
    private var drawShipSubmarino: Boolean = false
    private var drawShipDestructor: Boolean = false
    private var drawShipCrucero: Boolean = false

    private var isLanchaCreated: Boolean = false
    private var shipLanchaX: Float = 0f
    private var shipLanchaY: Float = 0f

    private var isPortaAvionesCreated: Boolean = false
    private var shipPortaAvionesX: Float = 0f
    private var shipPortaAvionesY: Float = 0f

    private var isSubmarinoCreated: Boolean = false
    private var shipSubmarinoX: Float = 0f
    private var shipSubmarinoY: Float = 0f

    private var isDestructorCreated: Boolean = false
    private var shipDestructorX: Float = 0f
    private var shipDestructorY: Float = 0f

    private var isCruceroCreated: Boolean = false
    private var shipCruceroX: Float = 0f
    private var shipCruceroY: Float = 0f

    private final var total : Int = 0

    private final var shipOriginX: Float = 0f
    private final var shipOriginY: Float = 0f

    var ships: List<Ships> = MutableList(5) { Ships(0f, 0f) }
    var board = Board()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()

        val pink = ContextCompat.getColor(context, R.color.pink)
        val pink2 = ContextCompat.getColor(context, R.color.pink2)

        paint.color = pink

        val paint2 = Paint()
        paint2.color = pink2

        val paint3 = Paint()
        paint3.color = Color.DKGRAY

        for (j in 0..3){
            for (i in 0..3){
                canvas?.drawRect(originX + 2 * i * cellside, originY + 2 * j * cellside,originX + (2 * i + 1) * cellside, originY + (2 * j + 1) * cellside, paint)
                canvas?.drawRect(originX + (2 * i + 1)* cellside, originY + (2 * j + 1) * cellside,originX + (2 * i + 2) * cellside, originY + (2 * j + 2) * cellside, paint)
                canvas?.drawRect(originX +( 2 * i + 1) * cellside, originY + 2 * j * cellside,originX + (2 * i + 2 ) * cellside, originY +  (2 * j + 1) * cellside, paint2)
                canvas?.drawRect(originX +( 2 * i ) * cellside, originY + (2 * j  + 1) * cellside,originX + (2 * i + 1 ) * cellside, originY +  (2 * j + 2) * cellside, paint2)
            }
        }

        //ship horizontal
       // canvas?.drawRect(150f , 300f , 650f , 400f , paint3)

        //ship vertical
        //canvas?.drawRect(250f , 400f , 350f , 600f , paint3)

        //crear variables para guardar las coordenadas de los barcos y poder compararlas con las coordenadas de los disparos
               if (drawShipLancha) {
                       canvas?.drawRect(
                           shipLanchaX,
                           shipLanchaY,
                           shipLanchaX + 100f,
                           shipLanchaY   + 100f,
                           paint3
                       )
               }

        if (drawShipPortaAviones) {

            if (ships[4].shipOrientation == 0){
                if (shipPortaAvionesX + 500f > 950f){
                    Toast.makeText(context, "No se puede crear el barco en esa posición", Toast.LENGTH_SHORT).show()
                }else {
                    canvas?.drawRect(
                        shipPortaAvionesX,
                        shipPortaAvionesY,
                        shipPortaAvionesX + 500f,
                        shipPortaAvionesY + 100f,
                        paint3
                    )

                }
            }else{
                if (shipPortaAvionesY + 500f > 1500f){
                    Toast.makeText(context, "No se puede crear el barco en esa posición", Toast.LENGTH_SHORT).show()
                }else {
                    canvas?.drawRect(
                        shipPortaAvionesX,
                        shipPortaAvionesY,
                        shipPortaAvionesX + 100f,
                        shipPortaAvionesY + 500f,
                        paint3
                    )
                }
            }
        }

        if (drawShipSubmarino) {

            if (ships[2].shipOrientation == 0){
                if (shipSubmarinoX + 300f > 950f){
                    Toast.makeText(context, "No se puede crear el barco en esa posición", Toast.LENGTH_SHORT).show()
                }else {
                    canvas?.drawRect(
                        shipSubmarinoX,
                        shipSubmarinoY,
                        shipSubmarinoX + 300f,
                        shipSubmarinoY + 100f,
                        paint3
                    )
                }
            }else{
                if (shipSubmarinoY + 300f > 1500f){
                    Toast.makeText(context, "No se puede crear el barco en esa posición", Toast.LENGTH_SHORT).show()
                }else {
                    canvas?.drawRect(
                        shipSubmarinoX,
                        shipSubmarinoY,
                        shipSubmarinoX + 100f,
                        shipSubmarinoY + 300f,
                        paint3
                    )
                }
            }
        }

        if (drawShipDestructor) {

            if (ships[1].shipOrientation == 0){
                if (shipDestructorX + 200f > 950f){
                    Toast.makeText(context, "No se puede crear el barco en esa posición", Toast.LENGTH_SHORT).show()
                }else {
                    canvas?.drawRect(
                        shipDestructorX,
                        shipDestructorY,
                        shipDestructorX + 200f,
                        shipDestructorY + 100f,
                        paint3
                    )
                }
            }else{
                if (shipDestructorY + 200f > 1500f){
                    Toast.makeText(context, "No se puede crear el barco en esa posición", Toast.LENGTH_SHORT).show()
                }else {
                    canvas?.drawRect(
                        shipDestructorX,
                        shipDestructorY,
                        shipDestructorX + 100f,
                        shipDestructorY + 200f,
                        paint3
                    )
                }
            }
        }

        if (drawShipCrucero) {

            if (ships[3].shipOrientation == 0){
                if (shipCruceroX + 400f > 950f){
                    Toast.makeText(context, "No se puede crear el barco en esa posición", Toast.LENGTH_SHORT).show()
                }else {
                    canvas?.drawRect(
                        shipCruceroX,
                        shipCruceroY,
                        shipCruceroX + 400f,
                        shipCruceroY + 100f,
                        paint3
                    )
                }
            }else{
                if (shipCruceroY + 400f > 1500f){
                    Toast.makeText(context, "No se puede crear el barco en esa posición", Toast.LENGTH_SHORT).show()
                }else {
                    canvas?.drawRect(
                        shipCruceroX,
                        shipCruceroY,
                        shipCruceroX + 100f,
                        shipCruceroY + 400f,
                        paint3
                    )
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y

        var xOrigin: Float = 0f
        var yOrigin: Float = 0f

        val paint = Paint()
        paint.color = Color.RED
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (x!! > originX && x < originX + 800f && y!! > originY && y < originY + 800f) {
                    when (true){
                        (x > 150f &&  x < 250f) ->  xOrigin = 150f
                        (x > 250f &&  x < 350f) ->  xOrigin = 250f
                        (x >350f &&  x < 450f) ->  xOrigin = 350f
                        (x > 450f &&  x < 550f) ->  xOrigin = 450f
                        (x > 550f &&  x < 650f) ->  xOrigin = 550f
                        (x > 650f &&  x < 750f) ->  xOrigin = 650f
                        (x > 750f &&  x < 850f) ->  xOrigin = 750f
                        (x > 850f &&  x < 950f) ->  xOrigin = 850f
                        else -> {}
                    }
                    when (true){
                        (y > 200f &&  y < 300f) ->  yOrigin = 200f
                        (y > 300f &&  y < 400f) ->  yOrigin = 300f
                        (y > 400f &&  y < 500f) ->  yOrigin = 400f
                        (y > 500f &&  y < 600f) ->  yOrigin = 500f
                        (y > 600f &&  y < 700f) ->  yOrigin = 600f
                        (y > 700f &&  y < 800f) ->  yOrigin = 700f
                        (y > 800f &&  y < 900f) ->  yOrigin = 800f
                        (y > 900f &&  y < 1000f) ->  yOrigin = 900f
                        else -> {}
                    }
                        shipOriginX = xOrigin
                        shipOriginY = yOrigin

                        when(BoardActivity.shipSelected){
                            0 -> {
                                shipLanchaX = shipOriginX
                                shipLanchaY = shipOriginY
                                ships[0].shipPositionX = shipOriginX
                                ships[0].shipPositionY = shipOriginY
                                ships[0].shipSelected = 1
                                board.dropShip(ships[0])
                                drawShipLancha = board.pushShip(ships[0])
                                isLanchaCreated = true
                                total++
                            }
                            1 -> {
                                shipPortaAvionesX = shipOriginX
                                shipPortaAvionesY = shipOriginY
                                ships[4].shipPositionX = shipOriginX
                                ships[4].shipPositionY = shipOriginY
                                ships[4].shipSelected = 5
                                ships[4].shipOrientation = BoardActivity.orientacion
                                board.dropShip(ships[4])
                                drawShipPortaAviones = board.pushShip(ships[4])
                                isPortaAvionesCreated = true
                                total++
                            }
                            2 -> {
                                shipSubmarinoX = shipOriginX
                                shipSubmarinoY = shipOriginY
                                ships[2].shipPositionX = shipOriginX
                                ships[2].shipPositionY = shipOriginY
                                ships[2].shipSelected = 3
                                ships[2].shipOrientation= BoardActivity.orientacion
                                board.dropShip(ships[2])
                                drawShipSubmarino = board.pushShip(ships[2])
                                isSubmarinoCreated = true
                                total++
                            }
                            3 -> {
                                shipDestructorX = shipOriginX
                                shipDestructorY = shipOriginY
                                ships[1].shipPositionX = shipOriginX
                                ships[1].shipPositionY = shipOriginY
                                ships[1].shipSelected = 2
                                ships[1].shipOrientation = BoardActivity.orientacion
                                board.dropShip(ships[1])
                                drawShipDestructor = board.pushShip(ships[1])
                                isDestructorCreated = true
                                total++
                            }
                            4 -> {
                                shipCruceroX = shipOriginX
                                shipCruceroY = shipOriginY
                                ships[3].shipPositionX = shipOriginX
                                ships[3].shipPositionY = shipOriginY
                                ships[3].shipSelected = 4
                                ships[3].shipOrientation = BoardActivity.orientacion
                                board.dropShip(ships[3])
                                drawShipCrucero = board.pushShip(ships[3])
                                isCruceroCreated = true
                                total++
                            }
                            else -> {
                                Toast.makeText(context, "No puedes crear más barcos", Toast.LENGTH_SHORT).show()
                            }
                        }

                        invalidate()
                        //Toast.makeText(context, "x: $x, y: $y  xOrigen: $xOrigin yOrigen: $yOrigin", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onTouchEvent(event)
    }
}
