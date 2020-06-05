package com.bj.librarylayout

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.bj.librarylayout.util.MeasureUtil
import java.math.BigDecimal

/**
 * 横向加载进度条
 */
class LoadingView2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
    context,
    attrs,
    defStyleAttr
) {

    private val TAG: String = "LoadingView"

    /*中间字体颜色*/
    private var textColor = Color.parseColor("#FFFFFF")

    /*中间字体文本大小*/
    private var textSize = MeasureUtil.dp2px(context, 10F)

    /*边的宽度*/
    private var borderWidth = MeasureUtil.dp2px(context, 0f)

    /*边的颜色*/
    private var borderColor = Color.parseColor("#EA4335")

    /*填充颜色*/
    private var strokeColor = Color.parseColor("#FCD153")

    init {
        attrs?.let {
            val typed = context.obtainStyledAttributes(it, R.styleable.LoadingView)
            textColor = typed.getColor(R.styleable.LoadingView_textColor, textColor)
            textSize = typed.getDimensionPixelSize(R.styleable.LoadingView_textSize, 10)
            borderWidth = typed.getDimensionPixelSize(R.styleable.LoadingView_borderWidth, 0)
            borderColor = typed.getColor(R.styleable.LoadingView_outColor, borderColor)
            strokeColor = typed.getColor(R.styleable.LoadingView_innerColor, strokeColor)
            textAlign = typed.getInt(R.styleable.LoadingView_textAlign, 1)
            typed.recycle()
        }
    }

    /*中间的文字位置,默认居中*/
    private var textAlign: Int = 0


    private val mPaint by lazy {
        Paint()
    }

    private val tmpWidth = MeasureUtil.dp2px(context, 200.0F)
    private val tmpHeight = MeasureUtil.dp2px(context, 100.0F)

    private val mReactF = RectF(0f, 0F, tmpWidth.toFloat(), tmpHeight.toFloat())
    private val progressPaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
        color = strokeColor
        xfermode = PorterDuffXfermode(PorterDuff.Mode.SCREEN)
    }

    //内部矩形
    private val mReactFInner = RectF()
    private val startTmpRectF = RectF()
    private val textRect = Rect()

    @SuppressLint("DrawAllocation")
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
            mReactFInner.left = -mReactF.width() + borderWidth+mul(mReactF.width(), (div(percent.toFloat(), 100F)))
            mReactFInner.top = 0F + borderWidth
            mReactFInner.right =mReactFInner.left+mReactF.width()-borderWidth*2
            mReactFInner.bottom = height.toFloat() - borderWidth
            canvas.drawRoundRect(
                mReactFInner,
                height.toFloat(),
                height.toFloat(),
                progressPaint
            )
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


    /**
     * Start animator.
     *
     * @param timeInterpolator the interpolator to be used by this animation. The default value is
     * android.view.animation.AccelerateInterpolator.
     *
     * @param duration the length of the animation.
     */
    @SuppressLint("ObjectAnimatorBinding")
    @JvmOverloads
    fun startAnimator(
        timeInterpolator: TimeInterpolator? = AccelerateInterpolator(),
        duration: Long
    ) =
        with(ObjectAnimator.ofFloat(this, "percent", 0f, percent.toFloat())) {
            interpolator = timeInterpolator
            this.duration = duration
            start()
        }

}