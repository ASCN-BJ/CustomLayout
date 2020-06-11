package com.bj.librarylayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.bj.librarylayout.util.MeasureUtil

class TextPathView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val textSize = MeasureUtil.dp2px(context, 19F)

    private val mPaint = Paint().apply {
        isAntiAlias = true
        //一定要记得设置类型
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        color = Color.parseColor("#000000")
        strokeWidth = MeasureUtil.dp2px(context, 1F).toFloat()
    }
    private val mTextPaint = Paint().apply {
        isAntiAlias = true
        textSize = MeasureUtil.dp2px(context,20F).toFloat()
        //一定要记得设置类型
        style = Paint.Style.FILL_AND_STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        color = Color.parseColor("#000000")
        strokeWidth = MeasureUtil.dp2px(context, 1f).toFloat()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        val centerX = width.div(2).toFloat()
        val centerY = height.div(2).toFloat()
        val path = Path()


        val effect=CornerPathEffect(500f)

        path.moveTo(0F, centerY)
        path.lineTo(centerX/3, centerY/3)

        path.lineTo(centerX, centerY)
        path.lineTo(width.toFloat(), centerY/3)
//        path.addCircle(centerX,centerY,50f,Path.Direction.CW)
        mPaint.pathEffect=effect
        canvas.drawPath(path, mPaint)
//        canvas.drawLine(0F,0F,centerX,centerY,mPaint1)

        val text = "好好学习,天天向上好好学习,天天向上好好学习,天天向上,好好学习,天天向上,好好学习,天天向上,好好学习,天天向上"
        canvas.drawTextOnPath(text, path, 10F, 0F, mTextPaint)
    }
}