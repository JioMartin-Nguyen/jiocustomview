package jio.customview.app.presentation.presenter

import android.content.Intent
import jio.customview.app.domain.model.modelstandard.common.Attachment
import jio.customview.app.presentation.view.ImagePreviewView
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class ImagePreviewPresenter : BasePresenter {

    private val subjectCheckExist: PublishSubject<Triple<Attachment, Int, Boolean>>  by lazy {
        return@lazy PublishSubject.create<Triple<Attachment, Int, Boolean>>()
    }

    fun onPageSelected(position: Int) {

    }

    fun getDataFromPreviousScreen(intent: Intent?) {
        val attachment1 = Attachment();
        attachment1.url = "http://taihinhnendoc.com/wp-content/uploads/2017/05/cap-doi-hoat-hinh-de-thuong.jpg"

        val attachment2 = Attachment();
        attachment2.url = "https://img.thuthuatphanmem.vn/uploads/2018/10/10/hinh-anh-hoat-hinh-dep-tham-tu-conan_052252628.jpg"

        val attachment3 = Attachment();
        attachment3.url = "https://depdrama.com/wp-content/uploads/2016/11/hinh-anh-hoat-hinh-dep-nhat-danh-cho-con-gai-14.jpg"

        val attachment4 = Attachment();
        attachment4.url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMTV3bK-Qy8WfLZ66msv2G4M0POI1yGxHnk-SDqJ8o-r50z82b7A"


        val attachment5 = Attachment();
        attachment5.url = "https://i.ytimg.com/vi/l5FNIjUE8rM/maxresdefault.jpg"

        val attachment6 = Attachment();
        attachment6.url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBJGx7DT3KHkhsTmayYKZuzGvrCVgveVy9RUw7T-H_BRTGK6X8cw"


        val attachment7 = Attachment();
        attachment7.url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcReHXc0B5pI9cHev3UGQxOgYkrjbgJWiGeJvS_YjE1tcZVyKRbP"

        val attachment8 = Attachment();
        attachment8.url = "https://tuhaovietnam.com.vn/wp-content/uploads/2019/01/hinh-anh-hoat-hinh-ngo-nghinh-13.jpg"


        val attachment9 = Attachment();
        attachment9.url = "http://taihinhnendoc.com/wp-content/uploads/2017/05/cap-doi-hoat-hinh-de-thuong.jpg"

        val attachment10 = Attachment();
        attachment10.url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfyndNVEuHQLyE5MHJ4vN8hPdgOuLqOKU_6n9lsd7l3JqataTGbA"

        val attachment11 = Attachment();
        attachment11.url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTC3bf0-Ul-LwfN7lFdLxjOAQo0AlQQMI83ekA8tyWcRLKbXn0b"

        val attachment12 = Attachment();
        attachment12.url = "https://img.thuthuatphanmem.vn/uploads/2018/10/16/anh-hoat-hinh-vui-nhon_042020668.jpg"

        listAttachment.add(attachment1)
        listAttachment.add(attachment2)
        listAttachment.add(attachment3)
        listAttachment.add(attachment4)
        listAttachment.add(attachment5)
        listAttachment.add(attachment6)
        listAttachment.add(attachment7)
        listAttachment.add(attachment8)
        listAttachment.add(attachment9)
        listAttachment.add(attachment10)
        listAttachment.add(attachment11)
        listAttachment.add(attachment12)

        for (i in 0..(listAttachment.size - 1)) {
            listAttachment[i].name = "attachment ${(i + 1)}"
            listAttachment[i].mimeType = "image/jpg"
        }


    }

    private var mView: ImagePreviewView? = null
    val listAttachment: ArrayList<Attachment> = arrayListOf()

    constructor(baseView: ImagePreviewView) {
        mView = baseView
    }


    //region check exist
    private fun initSubject() {

    }


    fun onCheckAttachment(position: Int, isFirstChoose: Boolean) {
        val attachment = listAttachment[position]
        subjectCheckExist.onNext(Triple(attachment, position, isFirstChoose))
    }
    //endregion check exist
}