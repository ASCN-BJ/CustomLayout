package com.bj.librarylayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.bj.librarylayout.util.MeasureUtil

class RoundImageView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = MeasureUtil.dp2px(context, 1F).toFloat()
        color = Color.parseColor("#FFFF00")
    }

    private val camera = Camera()

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        canvas.save()
        val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.wx_share_launcher)
        val centerX = width / 2f
        val centerY = height / 2f
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

//        val matrix=Matrix()
//        canvas.skew(0.1f,0f)

//        canvas.scale(0.5f,0.5f,centerX,centerY)
//        canvas.rotate(90f,centerX,centerY)

//        canvas.translate(-centerX, -centerY)
//        canvas.save()
//        canvas.translate(centerX, centerY)
//        canvas.restore()
        //行为操作按照操作相反的方向执行
//        canvas.rotate(45f, centerX, centerY)
//        canvas.rotate(45f, centerX, centerY)
//        canvas.rotate(45f)
//        canvas.translate(centerX,centerY)
//        canvas.rotate(45f)


//        camera.save()
//        camera.setLocation(0F,0F,-10F)
//        camera.rotateX(45F)
//        canvas.translate(centerX,centerY)
//
//        camera.applyToCanvas(canvas)
//
//        canvas.translate(-centerX,-centerY)
//        camera.restore()
        //Matrix

        val matrix = Matrix()
//        matrix.preTranslate(centerX,centerY)
//        matrix.postRotate(45F,centerX,centerY)
        val src = floatArrayOf(0F, 0f,width.toFloat(),height.toFloat())
        val dsc = floatArrayOf(100F, 100F,width.toFloat()-200f,height.toFloat()-300f)
        //拉扯四个点
        matrix.setPolyToPoly(src, 0, dsc, 0, 2)

        canvas.concat(matrix)


        linePaint.color = Color.parseColor("#FFFF00")
        //绘制按顺序执行
        //画中心坐标
        canvas.drawLine(0F, 0F, width.toFloat(), height.toFloat(), linePaint)
        canvas.drawLine(width.toFloat(), 0F, 0F, height.toFloat(), linePaint)
        //画画布的四周
        canvas.drawRect(RectF(0F, 0F, width.toFloat(), height.toFloat()), linePaint)
//        canvas.drawBitmap(bitmap, 0f, 0f, paint)

        linePaint.color = Color.parseColor("#ff0000")
        canvas.drawRect(RectF(0F, 0F, width.toFloat() / 3, height.toFloat() / 3), linePaint)

        canvas.restore()

    }
}