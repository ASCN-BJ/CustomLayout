package com.bj.librarylayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class RoundImageView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        canvas.save()
        val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.ic_p)

        val path = Path()

        path.addCircle(
            height / 2.toFloat(),
            width / 2.toFloat(),
            height.coerceAtLeast(width) / 2.toFloat(),
            Path.Direction.CW
        )

//        canvas.clipPath(path)

//        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
//        paint.shader = shader

        canvas.translate(100F, 100F)
//        canvas.rotate(90F, 100F, 100F)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.restore()

    }
}