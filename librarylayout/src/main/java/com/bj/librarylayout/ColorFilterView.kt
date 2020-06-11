package com.bj.librarylayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.bj.librarylayout.util.MeasureUtil

class ColorFilterView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val mPaint by lazy {
        Paint()
    }
    private val mPaint1 by lazy {
        Paint()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val centerX = width.div(2)
        val centerY = height.div(2)

        val radius = width.coerceAtLeast(height).div(2).toFloat()


        val srcBitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.ic_p)
        val bitmapShader = BitmapShader(srcBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)

        mPaint.shader = bitmapShader

        mPaint.colorFilter = LightingColorFilter(0xFFFFFF, 0x003000)

        canvas!!.drawCircle(centerX.toFloat(), centerY.toFloat(), radius, mPaint)

//        val effect = DashPathEffect(floatArrayOf(50F, 5f), 5F)


        mPaint1.isAntiAlias = true
        mPaint1.strokeWidth = MeasureUtil.dp2px(context, 5F).toFloat()
        mPaint1.style = Paint.Style.STROKE
        mPaint1.color = Color.parseColor("#000000")

        val path = Path()
        path.moveTo(0F, 0F)

        path.addCircle(
            centerX.toFloat(),
            centerY.toFloat(),
//            radius.minus(mPaint1.strokeWidth.div(2)),
            (radius - MeasureUtil.dp2px(context, 5F) / 2),
            Path.Direction.CW
        )
//        val effect = PathDashPathEffect(
//            path, 1f, 0F,
//            PathDashPathEffect.Style.TRANSLATE
//        )
//        mPaint1.pathEffect = effect
        canvas!!.drawPath(path, mPaint1)

    }

}