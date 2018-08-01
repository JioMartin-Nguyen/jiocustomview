package jio.customview.app.jiorecycleview

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView

class DividerItemDecoration : RecyclerView.ItemDecoration {

    private var divider: Drawable? = null
    private var orientation: Int = 0
    private var dividerSize: Int = 0
    private var showDivider: Int = 0
    private var offset: Int = 0

    constructor(divider: Drawable, dividerSize: Int, showDivider: Int, offset: Int) : this(divider, Orientation.UNKNOWN, dividerSize, showDivider, offset)

    constructor(divider: Drawable, orientation: Int, dividerSize: Int, showDivider: Int, offset: Int) {
        this.orientation = orientation
        this.divider = divider
        this.dividerSize = dividerSize
        this.showDivider = showDivider
        this.offset = offset
    }

    interface Orientation {
        companion object {
            val UNKNOWN = -1
        }
    }

    interface ShowDivider {
        companion object {
            val NONE = 0
            val BEGINNING = 1
            val MIDDLE = 2
            val END = 4
        }
    }
}