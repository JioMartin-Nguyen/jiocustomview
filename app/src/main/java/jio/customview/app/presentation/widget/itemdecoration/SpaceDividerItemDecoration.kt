package jio.customview.app.presentation.widget.itemdecoration

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout

/**
 * Tạo khoảng cách (space) giữa các item với nhau, và chỗ đó hiển thị background của RecycleView
 * @param orientation truyền vào orientation của RecycleView
 * @param sizeSpace khoảng space - item decorate giữa các item
 * @param showLastItem có show divider sau item cuối cùng hay không?
 */
class SpaceDividerItemDecoration(var orientation: Int, var sizeSpace: Int, var showLastItem: Boolean) : RecyclerView.ItemDecoration() {

    companion object {
        const val ORIENT_VERTICAL = LinearLayout.VERTICAL
        const val ORIENT_HORIZONTAL = LinearLayout.HORIZONTAL
    }

    private var mOrientation = ORIENT_VERTICAL

    init {
        this.mOrientation = orientation
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val isLastItem = parent.getChildAdapterPosition(view) == parent.adapter.itemCount - 1
        if (!isLastItem || showLastItem) {
            if (mOrientation == ORIENT_VERTICAL) {
                outRect.bottom = sizeSpace
            } else {
                outRect.right = sizeSpace
            }
        }
    }
}