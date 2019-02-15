package jio.customview.app.presentation.view

import jio.customview.app.domain.model.modelstandard.common.Attachment
import java.io.File

/**
 * AP7002
 */

interface ImagePreviewView : BaseView {

    fun showData(listAttachment: ArrayList<Attachment>, position: Int)

    fun showAttachment(attachment: Attachment, position: Int, exist: Boolean)

    fun showDialogFileLoadFailure(position: Int)

    fun changeVisibilityToolbar()

    fun openOtherApp(urlAttachment: String)

    fun showTitleToolbar(title: String)

    fun showButtonDownload(isShow: Boolean)

    fun showButtonNextBack(showButtonBack: Boolean, showButtonNext: Boolean)

    fun updateShowItems(listFileNotError: List<Attachment>)

    fun showArrowNext(showArrowNext: Boolean)

    fun scrollToPosition(initialPosition: Int)

    fun showPopupDownloadSuccess(fileDownload: File?)
}