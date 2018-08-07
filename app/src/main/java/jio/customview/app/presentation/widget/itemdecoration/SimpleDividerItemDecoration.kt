package jio.customview.app.presentation.widget.itemdecoration

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout

/**
 * Hiện tại chúng ta không tạo space giữa các view nữa, mà vẽ 1 divider thực sự giữa các view
 * override onDraw để vẽ divider lên space
 */

class SimpleDividerItemDecoration(var orientation: Int = ORIENTATION_VERTIVAL, var sizeDivider: Int, var hasDividerLast: Boolean) : RecyclerView.ItemDecoration() {

    companion object {
        const val ORIENTATION_VERTIVAL = LinearLayout.HORIZONTAL
        const val ORIENTATION_HORIZONTAL = LinearLayout.HORIZONTAL
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
       // Chung ta xac dinh 4 toa do 4 canh de ve divider: left, right, top, bottom
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

        }
        // Sau khi xac dinh 4 canh, ve drawable len canvas
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val isLastItem = parent.getChildAdapterPosition(view) == parent.adapter.itemCount - 1
        if (!isLastItem && hasDividerLast) {
            if (orientation == ORIENTATION_VERTIVAL) {
                outRect.bottom = sizeDivider
            } else {
                outRect.right = sizeDivider
            }
        }
    }
}