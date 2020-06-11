package com.bj.librarylayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.bj.librarylayout.util.MeasureUtil

class DrawTextView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0
) :
    View(context, attrs, defStyleAttr) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        textSize = MeasureUtil.dp2px(context, 30F).toFloat()
    }
    val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize=MeasureUtil.dp2px(context,19F).toFloat()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        val text = "将进酒⑴\n" +
                "书法作品《将进酒》\n" +
                "书法作品《将进酒》(13张)\n" +
                "君不见黄河之水天上来⑵，奔流到海不复回。\n" +
                "君不见高堂明镜悲白发，朝如青丝暮成雪⑶。\n" +
                "人生得意须尽欢⑷，莫使金樽空对月。\n" +
                "天生我材必有用，千金散尽还复来。\n" +
                "烹羊宰牛且为乐，会须一饮三百杯⑸。\n" +
                "岑夫子，丹丘生⑹，将进酒，杯莫停⑺。\n" +
                "与君歌一曲⑻，请君为我倾耳听⑼。\n" +
                "钟鼓馔玉不足贵⑽，但愿长醉不复醒⑾。\n" +
                "古来圣贤皆寂寞，惟有饮者留其名。\n" +
                "陈王昔时宴平乐，斗酒十千恣欢谑⑿。\n" +
                "主人何为言少钱⒀，径须沽取对君酌⒁。\n" +
                "五花马⒂、千金裘，呼儿将出换美酒，与尔同销万古愁⒃。 [1]"

//        val screenWidth=MeasureUtil.getScreenWidth(context)
        textPaint.isFakeBoldText=true
        val staticLayout =
            StaticLayout(text, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1F, 0F, true)
        staticLayout.draw(canvas)
    }
}