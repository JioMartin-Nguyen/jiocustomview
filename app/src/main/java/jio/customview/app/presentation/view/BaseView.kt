package jio.customview.app.presentation.view

/**
 * Created by dr.joyno054 on 2017/06/05.
 */
interface BaseView {

    fun logout()

    fun goToPlayStore()

    fun handleError(error: Throwable, option: Any? = null)

    fun isNetworkAvailable(): Boolean

    fun showDialogNoInternet()

    fun screenBack()

    fun showProgressDialog(isShow: Boolean)
}