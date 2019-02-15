package jio.customview.app.presentation.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jio.customview.app.R
import jio.customview.app.domain.model.modelstandard.common.Attachment

/**
 * AP7002
 */

class ImagePreviewAdapter(context: Context,
                          private val listAttachment: ArrayList<Attachment>) : PagerAdapter() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return listAttachment.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var itemView: View? = null
        val attachment: Attachment = listAttachment[position]

        if (attachment.isImage) {
            itemView = layoutInflater.inflate(R.layout.item_image_preview_detail, container, false)
        } else if (attachment.isVideo) {
            itemView = layoutInflater.inflate(R.layout.item_image_preview_detail, container, false)
        }
        itemView?.setTag(R.string.preview_view_tag, attachment.fileId)

        if (itemView != null) {
            container.addView(itemView, 0)
        }

        return itemView!!
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    fun setData(listAttachment: ArrayList<Attachment>) {
        this.listAttachment.clear()
        this.listAttachment.addAll(listAttachment)
        notifyDataSetChanged()
    }

    fun addData(listAttachment: List<Attachment>) {
        this.listAttachment.addAll(listAttachment)
        notifyDataSetChanged()
    }

}