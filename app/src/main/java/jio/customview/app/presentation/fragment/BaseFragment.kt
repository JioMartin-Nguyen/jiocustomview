package jio.customview.app.presentation.fragment

import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import butterknife.ButterKnife
import jio.customview.app.presentation.view.BaseView

open class BaseFragment : Fragment(), BaseView {
    override fun logout() {
        Log.i("BaseFragment","logout")
    }

    override fun goToPlayStore() {
        Log.i("BaseFragment","goToPlayStore")
    }

    override fun handleError(error: Throwable, option: Any?) {
        Log.i("BaseFragment","handleError")
    }

    override fun isNetworkAvailable(): Boolean {
        Log.i("BaseFragment","isNetworkAvailable")
        return true
    }

    override fun showDialogNoInternet() {
        Log.i("BaseFragment","showDialogNoInternet")
    }

    override fun screenBack() {
        Log.i("BaseFragment","screenBack")
    }

    override fun showProgressDialog(isShow: Boolean) {
        Log.i("BaseFragment","showProgressDialog")
    }

    fun inflateView(resId: Int): View {
        val view = View.inflate(context, resId, null)
        ButterKnife.bind(this, view)
        return view
    }
}