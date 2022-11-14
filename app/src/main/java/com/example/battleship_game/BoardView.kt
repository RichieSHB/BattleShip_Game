package com.example.battleship_game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import java.util.*

class BoardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private final val cellside: Float = 100f
    private final val originX: Float = 150f
    private final val originY: Float = 200f

    private var drawShipLancha: Boolean = false
    private var drawShipPortaAviones: Boolean = false

    private var isLanchaCreated: Boolean = false
    private var shipLanchaX: Float = 0f
    private var shipLanchaY: Float = 0f

    private var isPortaAvionesCreated: Boolean = false
    private var shipPortaAvionesX: Float = 0f
    private var shipPortaAvionesY: Float = 0f

    private final var total : Int = 0

    private final var shipOriginX: Float = 0f
    private final var shipOriginY: Float = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color = Color.CYAN

        val paint2 = Paint()
        paint2.color = Color.BLUE

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
                   if (isLanchaCreated){
                       shipLanchaX = shipOriginX
                       shipLanchaY = shipOriginY
                          isLanchaCreated = false
                   }
                   canvas?.drawRect(
                       shipLanchaX,
                       shipLanchaY,
                       shipLanchaX + 100f,
                       shipLanchaY   + 200f,
                       paint3
                   )
               }

        if (drawShipPortaAviones) {
           if (isPortaAvionesCreated) {
               shipPortaAvionesX = shipOriginX
               shipPortaAvionesY = shipOriginY
               isPortaAvionesCreated = false
           }
            canvas?.drawRect(
                shipPortaAvionesX,
                shipPortaAvionesY,
                shipPortaAvionesX + 100f,
                shipPortaAvionesY + 400f,
                paint3
            )
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

                        when(total){
                            0 -> {
                                drawShipLancha = true
                                isLanchaCreated = true
                                total++
                            }
                            1 -> {
                                drawShipPortaAviones = true
                                isPortaAvionesCreated = true
                                total++
                            }
                            2 -> {
                                Toast.makeText(context, "No puedes crear más barcos", Toast.LENGTH_SHORT).show()
                            }
                        }

                        invalidate()
                        Toast.makeText(context, "x: $x, y: $y  xOrigen: $xOrigin yOrigen: $yOrigin", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onTouchEvent(event)
    }
}
