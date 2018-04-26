package jio.customview.app.example

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class SmileView : View {

    // Khai bao cac bien
    private lateinit var circlePaint: Paint
    private lateinit var eyeAndMouthPaint: Paint

    private var centerX: Float = 0F
    private var centerY: Float = 0F
    private var radius: Float = 0F
    /**
     * arcBounds: Hình chữ nhật bao quanh vong cung sẽ vẽ
     */
    private val arcBounds = RectF()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaints()
    }

    /**
     * Cho phép layout cha - ViewGroup (như FrameLayout) có thể căn chỉnh custom view
     * Func cung cấp 1 tập measureSpecs để có thể xác định được chiều dài và chiều cao view
     * Ví dụ: hình vuông =  cho chiều dài và chiều cao bằng nhau
     *
     * func onMeasure phải gọi func setMeasureDimension(..) ít nhất 1 lần
     * Nếu không view sẽ crash với lỗi IllegalStateException
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)

        val size = Math.min(w, h)
        setMeasuredDimension(size, size)
    }

    /**
     * Cho phép lấy được chiều cao và chiều dài của view dùng để tính toán các thuộc tính trong quá trình rendering
     * Tại đây ta tính toán tâm và bán kính
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // Toa do tam cua mat vang
        centerX = w / 2F
        centerY = h / 2F

        // radius
        radius = ((Math.min(w, h)) / 2).toFloat()
    }

    /**
     * Cung cấp các func để vẽ view
     * func cung cấp 1 đối tượng canvas để bạn vẽ lên đó
     * Sử dụng draw Arc( vẽ vòng cung)
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // draw face
        canvas.drawCircle(centerX, centerY, radius, circlePaint)

        //draw eyes
        val eyeRadius = radius / 5F
        val eyeOffsetX = radius / 3F
        val eyeOffsetY = radius / 3F

        canvas.drawCircle(centerX - eyeOffsetX, centerY - eyeOffsetY, eyeRadius, eyeAndMouthPaint)
        canvas.drawCircle(centerX + eyeOffsetX, centerY - eyeOffsetY, eyeRadius, eyeAndMouthPaint)

        //draw mouth
        val mouthInset = radius / 3F
        arcBounds.set(mouthInset, radius, radius * 2 - mouthInset, radius * 2 - mouthInset)
        /**
         * arcBounds : Hinh chữ nhật bao quanh
         * startAngle : Góc bắt đầu vẽ
         * sweepAngle: Cung vẽ bao nhiêu
         * useCenter: Có sử dụng tâm để vẽ: true có nét vẽ từ tâm của hcn với cung
         *                                  false chỉ vẽ cung theo tâm nhưng không có nét vẽ nối với tâm hcn
         *
         *  paint : paint đùng để vẽ lên canvas
         */
        canvas.drawArc(arcBounds, 45f, 90f, true, eyeAndMouthPaint)

    }

    /** Gia tri can thiet cho but vu
     * 1. Tạo các Paint
     * Paint là đối tượng xác định các đối tượng khác vẽ ra như thế nào:
     * - Màu sẵc
     * - Style cho các nét vẽ
     *
     * Example tạo 2 Paint:
     * -1: Một Paint để Vẽ một hình tròn đặc màu vàng
     * -2: Một Paint để Vẽ các đường màu đen cho mắt và miệng
     * */
    private fun initPaints() {
        // Thiết lập paint for vẽ hình tròn đặc màu vàng
        circlePaint = Paint(Paint.ANTI_ALIAS_FLAG) // paint flag Paint.ANTI_ALIAS_FLAG: enable khử răng cưa khi vẽ
        circlePaint.style = Paint.Style.FILL
        circlePaint.color = Color.YELLOW

        // Thiết lập paint for painting eye and mouth with color black
        eyeAndMouthPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        eyeAndMouthPaint.style = Paint.Style.STROKE  // Paint có dạng nét chữ
        eyeAndMouthPaint.strokeWidth = 16 * resources.displayMetrics.density  // density: tỉ trọng, mật độ
        eyeAndMouthPaint.strokeCap = Paint.Cap.ROUND
        eyeAndMouthPaint.color = Color.BLACK
    }
}