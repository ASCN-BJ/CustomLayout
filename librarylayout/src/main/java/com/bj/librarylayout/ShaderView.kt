package com.bj.librarylayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class ShaderView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val mPaint = Paint().apply {
        isAntiAlias = true
    }
    private val mReact = Rect()

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        val startColor = Color.parseColor("#E91E63")
        val endColor = Color.parseColor("#2196F3")
        val lineShader = LinearGradient(
            0.toFloat(),
            0.toFloat(),
            width.toFloat(),
            height.toFloat(),
            startColor,
            endColor,
            Shader.TileMode.CLAMP
        )
//        val lineShader = RadialGradient(
//            width.toFloat().div(2),
//            height.toFloat().div(2),
//            height.toFloat().div(2),
//            startColor,
//            endColor,
//            Shader.TileMode.MIRROR
//        )

        mReact.top = 0
        mReact.left = 0
        mReact.right = width
        mReact.bottom = height


        val srcBitmap =
            BitmapFactory.decodeResource(context.resources, R.mipmap.ic_p)

        val bW = srcBitmap.width.toFloat()
        val bH = srcBitmap.height.toFloat()

        val sW = width * 2.div(bW)
        val sH = height * 2.div(bH)

        val matrix = Matrix().apply {
            postScale(sW, sH)
        }
        val desBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, bH.toInt(), bW.toInt(), matrix, true)


        val bitmapShader = BitmapShader(srcBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)


        val composeShader = ComposeShader(lineShader, bitmapShader, PorterDuff.Mode.DST_OVER)

        mPaint.shader = composeShader
//        mPaint.setColor(startColor)
//        canvas.drawRect(mReact, mPaint)


        canvas.drawCircle(
            width.toFloat().div(2),
            width.toFloat().div(2),
            width.toFloat().div(2),
            mPaint
        )


    }

}