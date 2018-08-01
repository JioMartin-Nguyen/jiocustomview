package jio.customview.app.jiorecycleview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import jio.customview.app.R

class JioRecycleView : RecyclerView {

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs, defStyle)
    }

    companion object {
        val DEFAULT_DIVIDER_ATTRS = intArrayOf(android.R.attr.listDivider)
    }

    private var showDivider: Int = 0

    @SuppressLint("Recycle")
    fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {
        initDefaultLayoutManager(attrs, defStyle)

        if (attrs != null) { // cau hinh voi xml
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.JioRecycleView, defStyle, 0)   // obtain : Co duoc
            initDivider(typeArray)
        }
    }

    private fun initDefaultLayoutManager(attrs: AttributeSet?, defStyle: Int) {
        if (this.layoutManager == null) {
            this.layoutManager = LinearLayoutManager(context, attrs, defStyle, 0)
        }
    }

    private fun initDivider(typeArray: TypedArray) {
        // Tao can 1 drawable: 1, May custom truyen vao, 2: Khong co bo may lay default: android_divider hoac divider
        if (typeArray.hasValue(R.styleable.JioRecycleView_show_divider)) {
            var dividerDrawable: Drawable = getDefaultDivider()
            showDivider = typeArray.getInt(R.styleable.JioRecycleView_show_divider, DividerItemDecoration.ShowDivider.NONE)
            if (showDivider != DividerItemDecoration.ShowDivider.NONE) {
                dividerDrawable = typeArray.getDrawable(R.styleable.JioRecycleView_android_divider)
                if (dividerDrawable == null) {
                    dividerDrawable = typeArray.getDrawable(R.styleable.JioRecycleView_divider)
                    if (dividerDrawable == null) {
                        dividerDrawable = getDefaultDivider()
                    }
                }
            }

            val dividerHeight = typeArray.getDimension(R.styleable.JioRecycleView_divider_height, -1f)
            val itemSpace = typeArray.getDimension(R.styleable.JioRecycleView_item_space, 0f).toInt()


            val decoration = DividerItemDecoration(dividerDrawable, dividerHeight.toInt(), showDivider, itemSpace / 2)
            addItemDecoration(decoration)
        }
    }

    private fun getDefaultDivider(): Drawable {
        val attributes = context.obtainStyledAttributes(DEFAULT_DIVIDER_ATTRS)
        var dividerDrawable = attributes.getDrawable(0)
        attributes.recycle()

        if (dividerDrawable == null) {
            dividerDrawable = ColorDrawable(Color.GRAY)
        }

        return dividerDrawable
    }
}