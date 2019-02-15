package jio.customview.app.presentation.presenter

open class BasePresenter {
    interface BaseView {

        fun logout()

        fun goToPlayStore()

        fun handleError(error: Throwable, option: Any? = null)

        fun isNetworkAvailable(): Boolean

        fun showDialogNoInternet()

        fun screenBack()

        fun showProgressDialog(isShow: Boolean)
    }

}