package com.bj.librarylayout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * 环形饼状图
 */
class CirclePieCharView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val mPaint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
//        strokeWidth = 100F
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val mWidth = width
        val mHeight = height
        mPaint.strokeWidth = mHeight / 2 / 6.toFloat()

        val centerX = mWidth / 2
        val centerY: Int = mHeight / 2
        val radius = mWidth / 2 - mHeight / 2 / 3.toFloat()
        reactF.top = 0f+radius
        reactF.left = 0f+radius
        reactF.right = reactF.left+300
        reactF.bottom = reactF.top+300
//        reactF.right=radius.toFloat()
//        reactF.bottom=radius.toFloat()

        mPaint.color = Color.parseColor("#EAEAEA")
        drawCircle(-90F, 360f, centerX.toFloat(), centerY.toFloat(), radius.toFloat(), canvas)

        mPaint.color = Color.parseColor("#FE3B00")
        drawCircle(-60F, 40F, centerX.toFloat(), centerY.toFloat(), radius.toFloat(), canvas)

        mPaint.color = Color.parseColor("#A91CAA")
        drawCircle(-20F, 30f, centerX.toFloat(), centerY.toFloat(), radius.toFloat(), canvas)

        mPaint.color = Color.parseColor("#02BBD1")
        drawCircle(10f, 50f, centerX.toFloat(), centerY.toFloat(), radius.toFloat(), canvas)

        mPaint.color = Color.parseColor("#FFA92A")
        drawCircle(60f, 180f, centerX.toFloat(), centerY.toFloat(), radius.toFloat(), canvas)

    }

    val reactF by lazy { RectF() }
    private fun drawCircle(
        startAngle: Float,
        endAngle: Float,
        centerX: Float,
        centerY: Float,
        radius: Float,
        canvas: Canvas?
    ) {
        canvas ?: return
        canvas.drawArc(reactF, startAngle, endAngle, false, mPaint)

//        canvas.drawCircle(centerX, centerY, radius, mPaint)
    }


}