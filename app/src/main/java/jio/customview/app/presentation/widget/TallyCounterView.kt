package jio.customview.app.presentation.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import jio.customview.app.common.extension.dpToPx
import jio.customview.app.common.extension.dpToSp
import jio.customview.app.presentation.view.TallyCounter
import java.util.*

class TallyCounterView : View, TallyCounter {


    private val MAX_COUNT = 9999
    private val MAX_COUNT_STRING = "9999"
    private var mCount: Int = 0
    private lateinit var mDisplayCount: String
    private var mBackGroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mLinePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mNumberPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPointPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mBackgroundRect: RectF = RectF()
    private var mCornerRadius: Float = 0F


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        mBackGroundPaint.color = Color.BLUE

        mLinePaint.color = Color.YELLOW

        mNumberPaint.color = Color.WHITE
        mNumberPaint.textSize = Math.round(64F.dpToSp()).toFloat()

        mPointPaint.color = Color.RED
        mPointPaint.strokeWidth = 5F.dpToPx()

        mCornerRadius = Math.round(5F.dpToPx()).toFloat()

        setCount(0)
    }

    override fun reset() {
        setCount(0)
    }

    override fun increment() {
        setCount(mCount++)
    }

    override fun getCount(): Int {
        return mCount
    }

    override fun setCount(count: Int) {
        mCount = Math.min(count, MAX_COUNT)
        mDisplayCount = String.format(Locale.CHINA, "%04d", mCount)   // Ở đây tao muốn hiển thị ví dụ: 0001, 0099 chẳng hạn
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val fontMetrics = mNumberPaint.fontMetrics
        // Mesure maxium possible width of text
        val maxTextWidth = mNumberPaint.measureText(MAX_COUNT_STRING)
        // Uoc tinh chieu cao toi da có thể có của văn bản
        val maxTextHeight = -fontMetrics.top + fontMetrics.bottom  // Giống với chiều cao của dòng với + fontMetrics.leading = 0
        // Lay chieu cao van ban
        val heightText = fontMetrics.descent - fontMetrics.ascent
        // Lay chieu cao cua dong, leading la khoảng cách hàng đầu (khoảng cách giữa hai đường thẳng) thường bằng 0, nhưng bạn vẫn nên thêm nó
        val heightLine = fontMetrics.bottom - fontMetrics.top + fontMetrics.leading

        // Thêm padding vào tính toán chiều rộng tối đa
        val desireWidth = Math.round(maxTextWidth + paddingLeft + paddingRight)

        // Thêm padding vào tính toán chiều cao tối đa, tạo sao ở đây phải *2F
        val desireHeight = Math.round(maxTextHeight * 2F + paddingTop + paddingBottom)

        // Dàn xếp kich thước nà chế độ View này muốn với kích thước mà cha mẹ sẽ cho phép
        val measureWidth = reconcileSize(desireWidth, widthMeasureSpec)
        val measureHeight = reconcileSize(desireHeight, heightMeasureSpec)

        // Lưu trữ dimension đo được cuối cùng
        setMeasuredDimension(measureWidth, measureHeight)
    }

    /**
     * @param contentSize size của nội dung view
     * @param measureSpec A {@link android.view.View.MeasureSpec} passed by the parent.
     * @return kích thức phù hợp nhất với {@code contenntSize} trong khi tôn trọng các ràng buộc của parent
     */
    private fun reconcileSize(contentSize: Int, measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        when (mode) {
            MeasureSpec.EXACTLY -> {
                return specSize
            }

            MeasureSpec.AT_MOST -> {
                return if (contentSize < specSize) {
                    contentSize
                } else {
                    specSize
                }
            }

            MeasureSpec.UNSPECIFIED -> {
                return contentSize
            }

            else -> {
                return contentSize
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val canvasWidth = canvas.width.toFloat()
        val canvasHeight = canvas.height.toFloat()

        val centerX = canvasWidth * 0.5F

        mBackgroundRect.set(0F, 0F, canvasWidth, canvasHeight)
        canvas.drawRoundRect(mBackgroundRect, mCornerRadius, mCornerRadius, mBackGroundPaint)

        // Ve 1 duong thang can biet toa do cua 2 diem:
        // diem bat dau (0F, baseLineY)
        // diem ket thuc (canvasWidth, baseLineY)
        val baseLineY = Math.round(canvasHeight * 0.6F).toFloat()
        canvas.drawLine(0F, baseLineY, canvasWidth, baseLineY, mLinePaint)

        // Vẽ text với toa đọ x là textX, y là baseLineY (Tọa độ góc dưới bên trái của text)

        val textWidth = mNumberPaint.measureText(mDisplayCount)  // Lay duoc chieu rong cua text
        val textX = Math.round(centerX - textWidth * 0.5F).toFloat()
        canvas.drawText(mDisplayCount, textX, baseLineY, mNumberPaint)

        canvas.drawPoint(textX, baseLineY, mPointPaint)
    }
}