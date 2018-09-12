package jio.customview.app.presentation.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import jio.customview.app.R
import jio.customview.app.common.utils.DimensionUtill

class SettingButton : View {

    companion object {
        val DEFAULT_TEXT_COLOR = Color.parseColor("#5F5F5F")
        val DEFAULT_TEXT_SIZE = 11F
        val DEFAULT_LINE_BOTTOM_COLOR = Color.parseColor("#4D625F5A")
        val DEFAULT_LINE_BOTTOM_MARGIN = 8F
        val DEFAUL_LINE_BOTTOM_STROKE_HEIGHT = 1F
        val DEFAULT_DRAWABLE_PADDING = 6F
    }

    private var text: String = ""
    private var textColor: Int = DEFAULT_TEXT_COLOR
    private var textSize = DEFAULT_TEXT_SIZE
    private var drawable: Drawable? = null
    private var drawablePadding = DEFAULT_DRAWABLE_PADDING
    private var lineBottomVisible: Boolean = false
    private var lineBottomColor: Int = DEFAULT_LINE_BOTTOM_COLOR
    private var lineBottomHeight = DEFAUL_LINE_BOTTOM_STROKE_HEIGHT
    private var lineBottomMargin = DEFAULT_LINE_BOTTOM_MARGIN

    private val textPaint: Paint = Paint()
    private val linePaint: Paint = Paint()
    private val rectText: Rect = Rect()

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val style: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingButton, defStyleAttr, 0)
        text = style.getString(R.styleable.SettingButton_sb_text)
        textColor = style.getColor(R.styleable.SettingButton_sb_textColor, DEFAULT_TEXT_COLOR)
        textSize = style.getDimension(R.styleable.SettingButton_sb_textSize, DEFAULT_TEXT_SIZE)

        drawable = style.getDrawable(R.styleable.SettingButton_sb_src)
        drawablePadding = style.getDimension(R.styleable.SettingButton_sb_srcPadding, DEFAULT_DRAWABLE_PADDING)

        lineBottomVisible = style.getBoolean(R.styleable.SettingButton_sb_lineBottomVisible, false)
        lineBottomColor = style.getColor(R.styleable.SettingButton_sb_lineBottomColor, DEFAULT_LINE_BOTTOM_COLOR)
        lineBottomHeight = style.getDimension(R.styleable.SettingButton_sb_lineHeight, DEFAUL_LINE_BOTTOM_STROKE_HEIGHT)
        lineBottomMargin = style.getDimension(R.styleable.SettingButton_sb_lineBottomMargin, DEFAULT_LINE_BOTTOM_MARGIN)

        style.recycle()
        initPaint()
        calculateBoundText(text)
    }

    private fun initPaint() {
        initTextPaint()
        if (lineBottomVisible) {
            initLinePaint()
        }
    }

    private fun initTextPaint() {
        textPaint.isAntiAlias = true
        textPaint.textSize = DimensionUtill.dpToPx(textSize)
        textPaint.color = textColor
    }

    private fun initLinePaint() {
        linePaint.isAntiAlias = true
        linePaint.color = lineBottomColor
        linePaint.strokeWidth = lineBottomHeight
        linePaint.style = Paint.Style.STROKE
        linePaint.strokeCap = Paint.Cap.SQUARE
    }

    private fun calculateBoundText(text: String) {
        textPaint.getTextBounds(text, 0, text.length, rectText)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (drawable != null) {
            val xText = (width.toFloat() / 2) - (rectText.width() / 2) - rectText.left
            val yText: Float = (height.toFloat() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)
            canvas.drawText(text, xText, yText, textPaint)
        } else {
            val xIcon: Int = ((width.toFloat() / 2) - (drawable!!.intrinsicWidth) / 2).toInt()
            val yIcon: Int = ((height.toFloat() / 2) - (drawable!!.intrinsicHeight + drawablePadding + rectText.height()) / 2).toInt()
            drawable!!.setBounds(xIcon, yIcon, xIcon + drawable!!.intrinsicWidth, yIcon + drawable!!.intrinsicHeight)
            drawable!!.draw(canvas)

            val xText = (width.toFloat() / 2) - (rectText.width() / 2)
            val yText: Float = (yIcon + drawable!!.intrinsicHeight + drawablePadding) + (rectText.height() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)
            canvas.drawText(text, xText, yText, textPaint)
        }

        if (lineBottomVisible) {
            val y = height.toFloat() - lineBottomHeight
            canvas.drawLine(lineBottomMargin, y, width.toFloat() - lineBottomMargin, y, linePaint)
        }
    }
}