package jio.customview.app.presentation.fragment.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatImageView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.chrisbanes.photoview.PhotoView

import jio.customview.app.R
import jio.customview.app.common.permission.RxPermission
import jio.customview.app.domain.model.modelstandard.common.Attachment
import jio.customview.app.presentation.adapter.ImagePreviewAdapter
import jio.customview.app.presentation.fragment.BaseFragment
import jio.customview.app.presentation.presenter.ImagePreviewPresenter
import jio.customview.app.presentation.view.ImagePreviewView
import jio.customview.app.presentation.widget.CommonToolbar
import jio.customview.app.presentation.widget.HackyProblematicViewPager
import java.io.File

class CacheImageFragment : BaseFragment(), ImagePreviewView {

    private lateinit var presenter: ImagePreviewPresenter

    companion object {

        @JvmStatic
        fun newInstance() =
                CacheImageFragment().apply {
                }
    }

    @BindView(R.id.view_page)
    lateinit var viewPager: HackyProblematicViewPager

    @BindView(R.id.imv_back)
    lateinit var imbBack: AppCompatImageView

    @BindView(R.id.imv_next)
    lateinit var imbNext: AppCompatImageView

    @BindView(R.id.toolbar)
    lateinit var toolbar: CommonToolbar

    @BindView(R.id.layout_download)
    lateinit var layoutDownload: FrameLayout

    @BindView(R.id.imv_download)
    lateinit var imvDownload: AppCompatImageView

    private lateinit var adapter: ImagePreviewAdapter
    private lateinit var rxPermission: RxPermission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ImagePreviewPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflateView(R.layout.fragment_cache_image)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rxPermission = RxPermission(activity)
        initView()
        initData()
    }

    private fun initView() {
        toolbar.apply {
            setBackgroundResource(R.color.color_1)
            setNavigationIcon(R.drawable.ic_test)
            setNavigationOnClickListener {
                screenBack()
            }
            setColorTitleText(Color.WHITE)
            setEllipsizeTitle(1, TextUtils.TruncateAt.END)
        }

        adapter = ImagePreviewAdapter(context!!, presenter.listAttachment)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                toolbar.visibility = View.VISIBLE
                layoutDownload.visibility = View.VISIBLE
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                presenter.onPageSelected(position)
                showAttachment(presenter.listAttachment[position], position, false)
            }
        })

        imbBack.setOnClickListener {
            viewPager.setCurrentItem(viewPager.currentItem - 1, true)
        }
        imbNext.setOnClickListener {
            viewPager.setCurrentItem(viewPager.currentItem + 1, true)
        }
        imvDownload.setOnClickListener {
            onClickDownloadImage()
        }
    }

    private fun onClickDownloadImage() {

    }

    private fun initData() {
        presenter.getDataFromPreviousScreen(activity!!.intent)
        adapter.notifyDataSetChanged()
    }

    override fun scrollToPosition(initialPosition: Int) {
        viewPager.setCurrentItem(initialPosition, false)
    }

    private fun initAttachmentImage(itemView: View, attachment: Attachment, isExist: Boolean) {
        itemView as PhotoView
        itemView.scaleType = ImageView.ScaleType.FIT_CENTER

        Glide.with(itemView).load(attachment.url)
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .into(itemView)

        /*if (isExist) {
            itemView.isZoomable = true
            itemView.maximumScale = 10f
            itemView.loadMediaAttachmentDetail(attachment)
            itemView.setOnClickListener {
                presenter.onItemClicked(attachment)
            }
        } else {
            itemView.isZoomable = false
            itemView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            GlideApp.with(itemView).load(R.drawable.ic_test).into(itemView)
        }*/
    }

    override fun showAttachment(attachment: Attachment, position: Int, exist: Boolean) {
        var view: View? = null
        for (i in 0 until viewPager.childCount) {
            val fileId = viewPager.getChildAt(i)?.getTag(R.string.preview_view_tag)
            if (fileId == attachment.fileId) {
                view = viewPager.getChildAt(i)
                break
            }
        }

        if (view == null) {
            return
        }
        if (attachment.isImage) {
            initAttachmentImage(view, attachment, exist)
        } /*else {
            initAttachmentVideo(view, attachment, exist)
        }*/
    }


    override fun showData(listAttachment: ArrayList<Attachment>, position: Int) {
        adapter.setData(listAttachment)
    }

    override fun showDialogFileLoadFailure(position: Int) {
    }

    override fun changeVisibilityToolbar() {
    }

    override fun openOtherApp(urlAttachment: String) {
    }

    override fun showTitleToolbar(title: String) {
    }

    override fun showButtonDownload(isShow: Boolean) {
    }

    override fun showButtonNextBack(showButtonBack: Boolean, showButtonNext: Boolean) {
    }

    override fun updateShowItems(listFileNotError: List<Attachment>) {
    }

    override fun showArrowNext(showArrowNext: Boolean) {
    }

    override fun showPopupDownloadSuccess(fileDownload: File?) {
    }

}
