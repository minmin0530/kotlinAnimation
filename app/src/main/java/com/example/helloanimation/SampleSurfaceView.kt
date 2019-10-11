package com.example.helloanimation
import android.view.SurfaceView
import android.view.SurfaceHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff

class SampleSurfaceView(context: Context): SurfaceView(context), SurfaceHolder.Callback, Runnable {

    var _canvas: Canvas? = Canvas()
    var _holder: SurfaceHolder? = null
    var _paint: Paint = Paint()

    var yy: Float = 100.0f
    init {
        holder.addCallback(this)
    }
    override fun surfaceChanged(p0: SurfaceHolder, p1:Int, p2:Int, p3:Int) {
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        var canvas: Canvas? = p0?.lockCanvas()
        var paint: Paint = Paint()
        paint.textSize = 24.0f
        paint.setColor(Color.RED)

        canvas?.drawText("ABC", 100.0f, 100.0f, paint)

        p0?.unlockCanvasAndPost(canvas)

        _canvas = canvas
        _holder = p0
        _paint = paint

        var thread : Thread = Thread(this)
        thread.start()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun run() {

        while (true) {
            _canvas = _holder?.lockCanvas()
            _canvas?.drawColor(0, PorterDuff.Mode.CLEAR)
            _canvas?.drawText("ABC", 100.0f, yy, _paint)
            _holder?.unlockCanvasAndPost(_canvas)

            yy += 1.0f
        }

    }
}