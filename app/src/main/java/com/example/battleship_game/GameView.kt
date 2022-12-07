package com.example.battleship_game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.util.*

class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var GameActivity = context as GameActivity

    private final val cellside: Float = 100f
    private final val originX: Float = 150f
    private final val originY: Float = 200f

    private final var total : Int = 0

    private final var shots: MutableList<Shoot> = mutableListOf(Shoot())

    private final var shipOriginX: Float = 0f
    private final var shipOriginY: Float = 0f

    var ships: List<Ships> = MutableList(5) { Ships() }

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

        var shots = shots[0].getShots()

        for (i in shots.size-1 downTo 0){
            if (shots[i].hit){
                paint3.color = Color.RED
            }else{
                paint3.color = Color.DKGRAY
            }
            canvas?.drawRect(shots[i].Rect, paint3)
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


                    val shot = Shoot()
                    shot.hit = board.shootShip(xOrigin, yOrigin)
                    shot.Rect = Rect(xOrigin.toInt(), yOrigin.toInt(), (xOrigin + 100).toInt(), (yOrigin + 100).toInt())
                    shot.addShoot(shot.Rect, shot.hit)
                    shots.add(shot)

                    Toast.makeText(context , "${shots[0].puntuacion()}", Toast.LENGTH_SHORT).show()

                    if (shots[0].puntuacion()){
                        GameActivity.endGame()
                    }else {
                        GameActivity.changeTurn()
                    }


                    invalidate()
                    //Toast.makeText(context, "x: $x, y: $y  xOrigen: $xOrigin yOrigen: $yOrigin", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onTouchEvent(event)
    }
}
