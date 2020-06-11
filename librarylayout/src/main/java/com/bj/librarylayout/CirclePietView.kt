package com.bj.librarylayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.bj.librarylayout.util.MeasureUtil

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
    //界面重绘问题
    private val mTextPaint = Paint().apply {
        isAntiAlias = true
    }

    private val textBound = Rect()

    //先暂时写个boolean
    private var completeFlag = false

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val mWidth = width
        val mHeight = height
        val minWH = mWidth.coerceAtMost(mHeight)
        val strokeWidth = mHeight.div(12).toFloat()
        mPaint.strokeWidth = strokeWidth

        val centerX = mWidth.div(2)
        val centerY: Int = mHeight.div(2)
        val radius = minWH.div(2) - strokeWidth.minus(2)

        reactF.top = 0F + strokeWidth.div(2)
        reactF.left = 0F + strokeWidth.div(2)
        reactF.right = reactF.left + minWH - strokeWidth
        reactF.bottom = reactF.top + minWH - strokeWidth

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

        //绘制中间文字
        mTextPaint.color = Color.parseColor("#333333")
        val text = "LOADING"
        mTextPaint.textSize = MeasureUtil.dp2px(context, 19F).toFloat()

        mTextPaint.getTextBounds(text, 0, text.length, textBound)


        val startX = reactF.centerX() - textBound.width().div(2)
        val startY = reactF.centerY() + textBound.height().div(2)

        canvas!!.drawText(text, 0, text.length, startX, startY, mTextPaint)
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