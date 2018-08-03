package jio.customview.app.presentation.fragment

import android.support.v4.app.Fragment
import android.view.View
import butterknife.ButterKnife

open class BaseFragment : Fragment() {

    fun inflateView(resId: Int): View {
        val view = View.inflate(context, resId, null)
        ButterKnife.bind(this, view)
        return view
    }
}