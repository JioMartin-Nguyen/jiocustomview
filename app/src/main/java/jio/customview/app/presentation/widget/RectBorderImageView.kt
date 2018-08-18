package jio.customview.app.presentation.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import jio.customview.app.R

/**
 * Created by ChinhDV on 10/12/2017.
 * Modified by TuanNX on 15/06/2018
 * Commented by TuHA on 18/06/2018
 * Default: rectangle image view, color circle_image_border(#d9d8d7) stroke width 1dp
 * image style: rectangle
 */
open class RectBorderImageView : AppCompatImageView {

    companion object {
        const val IMAGE_RECTANGLE = 2
        const val IMAGE_RECTANGLE_FILL = 3
        const val IMAGE_RECTANGLE_FILL_AND_STROKE = 4
    }


    // Properties
    private var imageStyle = IMAGE_RECTANGLE
    private var imageRadius = 0f
    private var mBackgroundColor = ContextCompat.getColor(context, android.R.color.white)

    //region Set Attr Method
    private var strokeWidth = 0f
        set(value) {
            field = value
            borderPaint?.let {
                borderPaint?.strokeWidth = value
                requestLayout()
                invalidate()
            }
        }

    /**
     * Set color for stroke border
     */
    var colorBorder = ContextCompat.getColor(context, R.color.circle_image_avatar_border_gray)
        set(value) {
            field = value
            borderPaint?.let {
                borderPaint?.color = value
                invalidate()
            }
        }


    var colorFill = ContextCompat.getColor(context, R.color.rectangle_image_avatar_fill_gray)
        set(value) {
            field = value
            fillPaint?.let {
                fillPaint?.color = value
                invalidate()
            }
        }
    //endregion

    // Object used to draw
    private var borderPaint: Paint? = null
    private lateinit var rectF: RectF
    private lateinit var rectFill: RectF
    private lateinit var rectClip: RectF
    private lateinit var clipPath: Path
    private var fillPaint: Paint? = null


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }


    fun init(context: Context, attrs: AttributeSet?) {

        // Init border paint
        borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        borderPaint!!.style = Paint.Style.STROKE
        //borderPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)

        rectF = RectF()
        rectFill = RectF()
        rectClip = RectF()
        clipPath = Path()

        // Load the styled attributes and set their properties
        val style: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RectBorderImageView)

        try {
            imageStyle = style.getInteger(R.styleable.RectBorderImageView_image_shape, IMAGE_RECTANGLE)

            mBackgroundColor = style.getColor(R.styleable.RectBorderImageView_background_color, mBackgroundColor)

            if (imageStyle == IMAGE_RECTANGLE_FILL || imageStyle == IMAGE_RECTANGLE_FILL_AND_STROKE) {
                fillPaint = Paint(Paint.ANTI_ALIAS_FLAG)
                colorFill = style.getColor(R.styleable.RectBorderImageView_fill_shape_color, ContextCompat.getColor(context, R.color.rectangle_image_avatar_fill_gray))
                fillPaint!!.style = Paint.Style.FILL
            }

            // Init Border
            colorBorder = style.getColor(R.styleable.RectBorderImageView_stroke_shape_color, setColorBorderDefault())
            strokeWidth = style.getDimensionPixelSize(R.styleable.RectBorderImageView_image_dimen_border_stroke, resources.getDimensionPixelSize(R.dimen.circle_image_view_border)).toFloat()
            imageRadius = style.getDimensionPixelSize(R.styleable.RectBorderImageView_image_dimen_radius, resources.getDimensionPixelSize(R.dimen.circle_image_view_border)).toFloat()

        } finally {
            style.recycle()
        }
    }


    open fun setColorBorderDefault(): Int {
        return ContextCompat.getColor(context, R.color.circle_image_avatar_border_orange)
    }

    override fun onDraw(canvas: Canvas) {
        clipPath.reset()
        canvas.drawColor(mBackgroundColor)
        // IMAGE_RECTANGLE
        when (imageStyle) {
            IMAGE_RECTANGLE_FILL -> {
                fillPaint?.style = Paint.Style.FILL

                // Draw fill
                drawRectangleFill(canvas)
            }
            IMAGE_RECTANGLE_FILL_AND_STROKE -> {
                fillPaint?.style = Paint.Style.FILL_AND_STROKE

                // Draw border
                drawRectangleBorder(canvas)

                // Draw fill
                drawRectangleFill(canvas)


            }
            else -> {
                fillPaint?.style = Paint.Style.STROKE
                // Draw border
                drawRectangleBorder(canvas)
            }
        }

        /*val padding = imageRadius / 2f
        rectClip.set(0f, 0f, width.toFloat() - strokeWidth - padding, height.toFloat() - strokeWidth - padding)*/
        rectClip.set(strokeWidth, strokeWidth, width.toFloat() - strokeWidth, height.toFloat() - strokeWidth)
        clipPath.addRoundRect(rectClip, imageRadius, imageRadius, Path.Direction.CW)
        canvas.clipPath(clipPath)
        super.onDraw(canvas)
    }

    private fun drawRectangleBorder(canvas: Canvas) {
        borderPaint?.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        borderPaint?.style = Paint.Style.STROKE
        rectF.set(strokeWidth / 2, strokeWidth / 2, width.toFloat() - strokeWidth / 2, height.toFloat() - strokeWidth / 2)  // fix
        canvas.drawRoundRect(rectF, imageRadius, imageRadius, borderPaint)
    }

    private fun drawRectangleFill(canvas: Canvas) {
        borderPaint?.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
        borderPaint?.style = Paint.Style.FILL
        borderPaint?.color = colorFill
        // rectFill.set(strokeWidth, strokeWidth, width.toFloat() - strokeWidth, height.toFloat() - strokeWidth)
        // Ve an no ben duoi cua thang border
        rectFill.set(strokeWidth / 2, strokeWidth / 2, width.toFloat() - strokeWidth / 2, height.toFloat() - strokeWidth / 2)
        canvas.drawRoundRect(rectFill, imageRadius, imageRadius, borderPaint)
    }
}