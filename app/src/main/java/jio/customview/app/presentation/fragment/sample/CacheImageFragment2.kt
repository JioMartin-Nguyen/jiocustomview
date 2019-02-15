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
import butterknife.BindView
import jio.customview.app.R
import jio.customview.app.common.permission.RxPermission
import jio.customview.app.presentation.adapter.ImagePreviewAdapter
import jio.customview.app.presentation.fragment.BaseFragment
import jio.customview.app.presentation.widget.CommonToolbar
import jio.customview.app.presentation.widget.HackyProblematicViewPager

class CacheImageFragment2 : BaseFragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cache_image, container, false)
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

       // adapter = ImagePreviewAdapter(context, presenter.listAttachment)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                toolbar.visibility = View.VISIBLE
                layoutDownload.visibility = View.VISIBLE
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
               /* presenter.onPageSelected(position)
                presenter.onCheckAttachment(position, false)
                presenter.onCheckLoadMore(position)*/
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
        // presenter.getDataFromPreviousScreen(activity.intent)
    }
}