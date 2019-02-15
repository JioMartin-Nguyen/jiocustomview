package jio.customview.app.presentation.widget

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import jio.customview.app.R
import jio.customview.app.common.extension.dpToPx

class CommonToolbar : Toolbar {

    /**
     * Title at the center of Toolbar
     */
    lateinit var tvTitle: TextView

    /**
     * Sub-title at the center of Toolbar
     */
    lateinit var tvSubtitle: TextView

    /**
     * The icon in the end of the title center
     */
    private lateinit var imvIconEnd: AppCompatImageView

    /**
     * The icon in the start of the title
     */
    private lateinit var imvIconStart: AppCompatImageView

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    private fun init() {
        val view = LayoutInflater.from(context).inflate(R.layout.view_toolbar, this, false)
        tvTitle = view.findViewById(R.id.tv_title_toolbar)
        tvSubtitle = view.findViewById(R.id.tv_sub_title_toolbar)
        imvIconStart = view.findViewById(R.id.imv_icon_start_toolbar)
        imvIconEnd = view.findViewById(R.id.imv_icon_end_toolbar)
        this.addView(view)
        (view.layoutParams as LayoutParams).gravity = Gravity.CENTER
    }

    //region title
    fun setTitleText(title: String?) {
        tvTitle.text = title
    }

    fun setTitleText(title: Int) {
        tvTitle.setText(title)
    }

    fun setColorTitleText(color: Int) {
        tvTitle.setTextColor(color)
    }

    fun setTextStyle(styleTypeface: Int) {
        tvTitle.setTypeface(tvTitle.typeface, styleTypeface)
    }

    fun setEllipsizeTitle(line: Int, truncateAt: TextUtils.TruncateAt) {
        // CR #17701 - With android version low, need set singleLine = true
        tvTitle.setSingleLine(true)
        tvTitle.setLines(line)
        tvTitle.ellipsize = truncateAt
    }

    fun getTitleText(): String = tvTitle.text.toString()

    fun setOnClickTitle(onTitleClickListener: (() -> Unit)? = null) {
        tvTitle.setOnClickListener {
            onTitleClickListener?.invoke()
        }
    }
    //endregion title

    //region title
    fun setSubTitleText(subTitle: String) {
        tvSubtitle.text = subTitle
    }

    fun setSubTitleText(subTitle: Int) {
        tvSubtitle.setText(subTitle)
    }

    fun setVisibilitySubtitle(visibility: Int) {
        tvSubtitle.visibility = visibility
    }

    fun getSubTitleText(): String = tvSubtitle.text.toString()

    fun setOnClickSubTitle(onSubTitleClickListener: (() -> Unit)? = null) {
        tvSubtitle.setOnClickListener {
            onSubTitleClickListener?.invoke()
        }
    }
    //endregion title

    //region icon start
    fun setIconStart(resourceId: Int) {
        imvIconStart.setImageResource(resourceId)
    }

    fun setRotateIconStart(rotation: Float) {
        imvIconStart.rotation = rotation
    }

    fun getVisibilityIconStart(): Int = imvIconStart.visibility

    fun setVisibilityIconStart(visibility: Int) {
        imvIconStart.visibility = visibility
    }

    fun setDisableIconStart() {
        imvIconStart.apply {
            alpha = 0.3f
            isClickable = false
        }
    }

    fun setEnableIconStart() {
        imvIconStart.apply {
            alpha = 1.0f
            isClickable = true
        }
    }

    fun setOnClickIconStart(onIconStartClickListener: (() -> Unit)? = null) {
        imvIconStart.setOnClickListener {
            onIconStartClickListener?.invoke()
        }
    }

    fun setMarginIconStart(margin: Int) {
        val layoutParams = imvIconStart.layoutParams as LinearLayout.LayoutParams
        layoutParams.marginEnd = margin.toFloat().dpToPx().toInt()
    }
    //endregion icon start

    //region icon end
    fun setIconEnd(resourceId: Int) {
        imvIconEnd.setImageResource(resourceId)
    }

    fun setRotateIconEnd(rotate: Float) {
        imvIconEnd.rotation = rotate
    }

    fun getVisibilityIconEnd(): Int = imvIconEnd.visibility

    fun setVisibilityIconEnd(visibility: Int) {
        imvIconEnd.visibility = visibility
    }

    fun setOnClickIconEnd(onIconEndClickListener: (() -> Unit)? = null) {
        imvIconEnd.setOnClickListener {
            onIconEndClickListener?.invoke()
        }
    }

    fun setMarginIconEnd(margin: Int) {
        val layoutParams = imvIconEnd.layoutParams as LinearLayout.LayoutParams
        layoutParams.marginStart = margin.toFloat().dpToPx().toInt()
    }
    //endregion icon end
}