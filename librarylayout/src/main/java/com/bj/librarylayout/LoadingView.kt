package com.bj.librarylayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.bj.librarylayout.util.MeasureUtil
import java.math.BigDecimal

/**
 * 横向加载进度条
 */
class LoadingView : View {

    private val TAG: String = "LoadingView"

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initData(context, attrs)
    }

    /*中间字体颜色*/
    private var textColor = Color.parseColor("#FFFFFF")

    /*中间字体文本大小*/
    private var textSize = MeasureUtil.dp2px(context, 10F)

    /*边的宽度*/
    private var borderWidth = MeasureUtil.dp2px(context, 3F)

    /*边的颜色*/
    private var borderColor = Color.parseColor("#EA4335")

    /*填充颜色*/
    private var strokeColor = Color.parseColor("#FCD153")

    /*中间的文字位置,默认居中*/
    private var textAlign: Int = 0
    private fun initData(context: Context?, attrs: AttributeSet?) {
        val typed = context?.obtainStyledAttributes(attrs, R.styleable.LoadingView)
        typed ?: return
        textColor = typed.getColor(R.styleable.LoadingView_textColor, textColor)
        textSize = typed.getDimensionPixelSize(R.styleable.LoadingView_textSize, 10)
        borderWidth = typed.getDimensionPixelSize(R.styleable.LoadingView_borderWidth, 3)
        borderColor = typed.getColor(R.styleable.LoadingView_outColor, borderColor)
        strokeColor = typed.getColor(R.styleable.LoadingView_innerColor, strokeColor)
        textAlign = typed.getInt(R.styleable.LoadingView_textAlign, 1)

        typed.recycle()
    }


    private val mPaint by lazy {
        Paint()
    }

    private val tmpWidth = MeasureUtil.dp2px(context, 200.0F)
    private val tmpHeight = MeasureUtil.dp2px(context, 100.0F)

    private val mReactF = RectF(0f, 0F, tmpWidth.toFloat(), tmpHeight.toFloat())

    //内部矩形
    private val mReactFInner = RectF()
    private val startTmpRectF = RectF()
    private val textRect = Rect()
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        val width = measuredWidth
        val height = measuredHeight
        mReactF.right = width.toFloat()
        mReactF.bottom = height.toFloat()
        //画笔设置
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.isAntiAlias = true
        mPaint.strokeJoin = Paint.Join.ROUND
        //外边框
        mPaint.color = borderColor
        canvas.drawRoundRect(
            mReactF,
            height.toFloat(),
            height.toFloat(),
            mPaint
        )
        if ((mul(width.toFloat(), (div(percent.toFloat(), 100F))) - borderWidth) >= 1) {
            //内边框
            mReactFInner.left = 0F + borderWidth
            mReactFInner.top = 0F + borderWidth
            mReactFInner.right =
                (mul(width.toFloat(), (div(percent.toFloat(), 100F))) - borderWidth)

            mReactFInner.bottom = height.toFloat() - borderWidth
            mPaint.color = strokeColor
            //先画开始的扇形
            if (compare(mReactFInner.width(), mReactFInner.height()*2) <=0) {
                startTmpRectF.set(mReactFInner)
                canvas.drawArc(startTmpRectF, 90F, 180F, true, mPaint)
            } else {
                canvas.drawArc(startTmpRectF, 90F, 180F, true, mPaint)
                canvas.drawRoundRect(
                    mReactFInner,
                    height.toFloat(),
                    height.toFloat(),
                    mPaint
                )
            }
        }
        Log.d(TAG, "innerRect:$mReactFInner")
        //画中间的文字
        mPaint.color = textColor
        mPaint.textAlign = Paint.Align.CENTER
        mPaint.textSize = textSize.toFloat()
        val centerText = "$percent %"
        //获取文字的宽高
        mPaint.getTextBounds(centerText, 0, centerText.length, textRect)

        val centerX = width / 2
        val centerY = height / 2
        canvas.drawText(
            centerText,
            centerX.toFloat(),
            centerY.toFloat() + textRect.height() / 2,
            mPaint
        )

        Log.d(TAG, "宽度:" + measuredWidth + "高度:" + measuredHeight)
    }

    private var percent = 0

    fun updatePercent(mPercent: Int) {
        percent = if (mPercent >= 100) {
            100
        } else {
            mPercent
        }
        if (mPercent <= 100) {
            postInvalidate()
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    enum class TextAlign constructor(align: Int) {
        LEFT(0),
        CENTER(1),
        RIGHT(2)
    }

    private fun div(v1: Float, v2: Float): Float {
        return BigDecimal(v1.toDouble()).divide(BigDecimal(v2.toDouble())).toDouble().toFloat()
    }

    private fun mul(v1: Float, v2: Float): Float {
        return BigDecimal(v1.toDouble()).multiply(BigDecimal(v2.toDouble())).toDouble().toFloat()
    }

    private fun compare(v1: Float, v2: Float): Int {
        return BigDecimal(v1.toDouble()).compareTo(BigDecimal(v2.toDouble()))
    }

}