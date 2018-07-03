package jio.customview.app.example

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import jio.customview.app.R

class BorderImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {

    companion object {
        val SCALE_TYPE = ScaleType.CENTER_CROP
        const val IMAGE_CIRCLE = 1
        const val IMAGE_RECTANGLE = 2

        // Default Values
        const val DEFAULT_BORDER_WIDTH = 4f
        const val DEFAULT_SHADOW_RADIUS = 8f
    }

    // Properties
    private var borderWidth: Float = 0f
    private var canvasSize: Int = 0
    private var shadowRadius: Float = 0f
    private var shadowColor = Color.BLACK

    // Objesct use to draw
    private var image: Bitmap? = null
    private var mdrawable: Drawable? = null
    private var paint: Paint? = null
    private var paintBorder: Paint? = null

    init {
        init(context, attrs, defStyleAttr)
    }

    @SuppressLint("Recycle")
    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        // Init paint
        paint = Paint()
        paint!!.isAntiAlias = true  // enable khu rang cua khi ve

        paintBorder = Paint()
        paintBorder!!.isAntiAlias = true


        // Load the styled attributes and set their properties
        val attributes: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.BorderImageView, defStyleAttr, 0)

        try {
            // Init Border
            if (attributes.getBoolean(R.styleable.BorderImageView_bor_border, true)) {
                val defaultBorderSize = DEFAULT_BORDER_WIDTH * context.resources.displayMetrics.density
                setBorderWidthMe(attributes.getDimension(R.styleable.BorderImageView_bor_border_width, defaultBorderSize))
                setBorderColor(attributes.getColor(R.styleable.BorderImageView_bor_border_color, Color.WHITE))
            }

            // Init Shadow
            if (attributes.getBoolean(R.styleable.BorderImageView_bor_shadow, false)) {
                shadowRadius = DEFAULT_SHADOW_RADIUS
                // Vẽ theo shadowRadius và màu của nó
                drawShadow(attributes.getFloat(R.styleable.BorderImageView_bor_shadow_radius, shadowRadius), attributes.getColor(R.styleable.BorderImageView_bor_shadow_color, shadowColor))
            }
        } finally {
            attributes.recycle()  //? Lưu ý việc bạn sử dụng TypeArray sau khi các bạn sử dụng xong cần phải gọi fun recycle() để giải phóng bộ nhớ
        }
    }
    // endregion

    // region Set Attr Method
    private fun setBorderWidthMe(borderWidth: Float) {
        this.borderWidth = borderWidth
        requestLayout() // ?: Sử dụng hàm này khi bạn muốn cập nhạt và tính toán lại cả kích thước, vị trí của view và sau đó vẽ  lại view đó theo kích cỡ mới
        invalidate() //? Sử dụng để vẽ lại những trường hợp vẽ cơ bản: vd: update color, text, onTouch có nghĩa là: view chỉ gọi lại hàm onDraw() để cập nhật lại trạng thái
    }

    private fun setBorderColor(borderColor: Int) {
        paintBorder!!.color = borderColor
        invalidate()
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun drawShadow(shadowRadius: Float, shadowColor: Int) {
        this.shadowRadius = shadowRadius
        this.shadowColor = shadowColor

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, paintBorder)
        }
        paintBorder!!.setShadowLayer(shadowRadius, 0.0f, shadowRadius / 2, shadowColor)  // Thiết lập đổ bóng
    }
    //endregion

    // region OnMeasure
    /**
     * Trước khi bạn vẽ call method onDraw()
     * Trước hết chúng ta cần xác định được kích thước của view => onMeasure()
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureWidth(widthMeasureSpec)
        val height = measureHeight(heightMeasureSpec)
        //desiredWidth: dựa vào nội dung muốn hiển thị mà bạn sẽ tính ra bạn cần tối thiểu bao nhiêu
        //không gian để bạn hiển thị

        setMeasuredDimension(width, height) // xac nhan thong tin tinh toan tren voi ViewGroup
    }

    @SuppressLint("SwitchIntDef")
    private fun measureWidth(measureSpec: Int): Int {
        // Muc dich xac dinh kich thuoc chieu rong cua View
        val result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        result = when (specMode) {
            MeasureSpec.EXACTLY -> // The parent has determined an exact size for the child.
                // Cho biet rang width va height cua view da duoc set cho nhung gia tri cu the va chung ta se de size cua view la  size nay
                // Dieu nay co nghia la chung ta da xac dinh fix cung kich thuoc trong xml, nhu kieu: layout_width = 200dp
                specSize
            MeasureSpec.AT_MOST -> // The child can be as large as it wants up to the specified size.
                // Có nghĩa là layout_width hoặc layout_height đã được gán cho giá trị là match_parent hoặc wrap_content
                // KHONG NEN VUOT QUA GIOI HAN NAY, vay nen o day moi su dung lenh Math.min(desiredWith, widthSize)
                specSize
            else -> // -The parent has not imposed any constrainst on the child.
                // -MeasureSpec.UNSPECIFIED: Cho biết layout_width hoặc layout_height đã được gán cho giá trị wrap_content và không được giới hạn .
                // Trong trường họp này bạn nên định nghĩa một kích thước cho View.
                // - KHong ap buoc bat ky rang buoc nao voi Child (Cho ban thoa suc)
                canvasSize
        }
        return result
    }

    @SuppressLint("SwitchIntDef")
    private fun measureHeight(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        val result = when (specMode) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.AT_MOST -> specSize
            else -> canvasSize
        }
        return result + 2
    }
    //endregion

    override fun onDraw(canvas: Canvas) {
        // Load the bitmap
        loadBitmap()

        // Check if image isn't null
        if (image == null) return

        if (!isInEditMode) {
            canvasSize = canvas.width
            if (canvas.height < canvasSize) {
                canvasSize = canvas.height
            }
        }

        // circleCenter is the x or y of the view's center
        // radius is the radius in pixels of the cirle to be drawn
        // paint contains the shader that will texture the shape
        val circleCenter = (canvasSize - (borderWidth * 2)).toInt() / 2

        // Draw Border
        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter + borderWidth - (shadowRadius + shadowRadius / 2), paintBorder)
        // Draw CircularImageView
        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter - (shadowRadius + shadowRadius / 2), paint)
    }

    private fun loadBitmap() {
        if (mdrawable == drawable) {
            return
        }

        mdrawable = drawable
        this.image = drawableToBitmap(mdrawable)
        updateShader()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasSize = w
        if (h < canvasSize)
            canvasSize = h
        if (image != null)
            updateShader()
    }

    private fun updateShader() {
        if (image == null) {
            return
        }

        // Crop Center Image, tai sao cho nay can crop bit map center???
        image = cropBitmap(image!!)

        // Create Shader
        val shader = BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        // Center Image in Shader, tai sao lai lam nhu vay
        val matrix = Matrix()
        matrix.setScale(canvasSize / image!!.width.toFloat(), canvasSize / image!!.height.toFloat())
        shader.setLocalMatrix(matrix)

        // Set Shader in Paint
        paint!!.shader = shader
    }

    private fun cropBitmap(bitmap: Bitmap): Bitmap {
        val bmp: Bitmap = if (bitmap.width >= bitmap.height) {
            Bitmap.createBitmap(bitmap,
                    bitmap.width / 2 - bitmap.height / 2,   // Tai sao lai lam ntn
                    0,                                       // Tai sao y = 0
                    bitmap.height, bitmap.height
            )
        } else {
            Bitmap.createBitmap(bitmap,
                    0,                                      // Tai sao ntn
                    bitmap.height / 2 - bitmap.width / 2,   // Tai sao lai lam ntn, cai gi, ntn
                    bitmap.width, bitmap.width
            )
        }
        return bmp
    }

    private fun drawableToBitmap(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        } else if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val intrinsicWidth = drawable.intrinsicWidth  // Chieu rong noi tai
        val intrinsicHeight = drawable.intrinsicHeight  // Chieu dai noi tai

        if (!(intrinsicWidth > 0 && intrinsicHeight > 0)) {
            return null
        }

        return try {
            // Create Bitmap object out of the drawable
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        } catch (e: OutOfMemoryError) {
            // Simple return null of failure bitmap creations
            Log.e(BorderImageView::class.java.simpleName, "Encountered OutOfMemoryError while generating bitmap!")
            null
        }
    }

    //endregion
}