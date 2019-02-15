package jio.customview.app.common.extension

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import jio.customview.app.R
import jio.customview.app.common.model.ImagePath
import jio.customview.app.domain.model.modelstandard.common.Attachment

/*fun ImageView.loadImage(path: String?, placeHolder: Int = R.color.colorPrimary, error: Int = R.color.colorPrimary) {
    GlideApp.with(this.context)
            .load(path)
            .placeholder(placeHolder)
            .error(error)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(this)
}

fun ImageView.loadImage(uri: Uri?, placeHolder: Int = R.color.colorPrimary, error: Int = R.color.colorPrimary) {
    GlideApp.with(this.context)
            .load(uri)
            .placeholder(placeHolder)
            .error(error)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(this)
}

fun ImageView.loadMediaAttachment(attachment: Attachment,
                                  placeHolder: Int = R.color.colorPrimary,
                                  errorImage: Int = R.color.colorPrimary,
                                  errorVideo: Int = R.color.colorPrimary,
                                  onLoadVideoDone: (() -> Unit)? = null,
                                  onLoadVideoFailed: (() -> Unit)? = null) {
    when {
        !attachment.isUploaded -> {
            this.scaleType = ImageView.ScaleType.CENTER_CROP
            this.loadMediaLocal(attachment, placeHolder, errorImage, errorVideo)
        }
        attachment.isImage -> {
            if (attachment.isSvg) {
                this.scaleType = ImageView.ScaleType.CENTER_INSIDE
                this.loadImageAttachment(attachment.thumbnailPath, attachment.originalPath, 0, errorImage, true)
            } else {
                this.scaleType = ImageView.ScaleType.CENTER_CROP
                this.loadImageAttachment(attachment.thumbnailPath, attachment.originalPath, placeHolder, errorImage)
            }
        }
        attachment.isVideo -> {
            this.scaleType = ImageView.ScaleType.CENTER_CROP
            this.loadVideoAttachment(attachment, placeHolder, errorVideo, onLoadVideoDone, onLoadVideoFailed)
        }
    }
}

private fun ImageView.loadMediaLocal(attachment: Attachment, placeHolder: Int, errorImage: Int, errorVideo: Int) {
    val error = if (attachment.isImage) {
        errorImage
    } else {
        errorVideo
    }
    GlideApp.with(this)
            .load(attachment.url)
            .placeholder(placeHolder)
            .error(error)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(this)
}

private fun ImageView.loadImageAttachment(thumbnailPath: String?, originalPath: String?, placeHolder: Int = R.color.colorPrimary, error: Int = R.color.colorPrimary, isSVG: Boolean = false) {
    if (thumbnailPath.isNullOrEmpty() || originalPath.isNullOrEmpty()) {
        this.setImageResource(error)
        return
    }

    if (isSVG) {
        //#16988
        val ref = FirebaseStorage.getInstance().getReference(originalPath!!)
        GlideApp.with(context)
                .`as`(PictureDrawable::class.java)
                .load(ref)
                .placeholder(placeHolder)
                .error(error)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .listener(SvgDecoder.SvgSoftwareLayerSetter())
                .into(this)
    } else {
        val imagePath = ImagePath(thumbnailPath, originalPath)
        GlideApp.with(context)
                .load(imagePath)
                .placeholder(placeHolder)
                .error(error)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(this)
    }
}

private fun ImageView.loadVideoAttachment(attachment: Attachment, placeHolder: Int, error: Int, onLoadDone: (() -> Unit)?, onLoadFailed: (() -> Unit)?) {
    if (attachment.thumbnailPath.isNullOrBlank()) {
        this.setImageResource(error)
        return
    }

    val storage = FirebaseStorage.getInstance()
    val ref = storage.getReference(attachment.thumbnailPath!!)

    GlideApp.with(this.context)
            .load(ref)
            .placeholder(placeHolder)
            .error(error)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    onLoadFailed?.invoke()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    onLoadDone?.invoke()
                    return false
                }
            })
            .into(this)
}

fun ImageView.loadMediaAttachmentDetail(attachment: Attachment,
                                        placeHolder: Int = R.color.colorPrimary,
                                        errorVideo: Int = R.color.colorPrimary,
                                        errorImageDrawable: Int = R.drawable.ic_test,
                                        onLoadVideoDone: (() -> Unit)? = null,
                                        onLoadVideoFailed: (() -> Unit)? = null) {
    when {
        attachment.isImage -> {
            if (attachment.isSvg) {
                this.scaleType = ImageView.ScaleType.CENTER_INSIDE
                this.loadImageAttachmentDetail(attachment.thumbnailPath, attachment.originalPath, errorImageDrawable, true)
            } else {
                this.scaleType = ImageView.ScaleType.FIT_CENTER
                this.loadImageAttachmentDetail(attachment.thumbnailPath, attachment.originalPath, errorImageDrawable, false)
            }
        }
        attachment.isVideo -> {
            this.scaleType = ImageView.ScaleType.FIT_CENTER
            this.loadVideoAttachment(attachment, placeHolder, errorVideo, onLoadVideoDone, onLoadVideoFailed)
        }
    }
}

private fun ImageView.loadImageAttachmentDetail(thumbnailPath: String?,
                                                originalPath: String?,
                                                errorImageDrawable: Int,
                                                isSVG: Boolean = false) {

    if (thumbnailPath.isNullOrEmpty() || originalPath.isNullOrEmpty()) {
        this.setImageResource(errorImageDrawable)
        return
    }

    val originalRef = FirebaseStorage.getInstance().getReference(originalPath!!)
    val thumbnailRef = FirebaseStorage.getInstance().getReference(thumbnailPath!!)
    // val thumbnailRef = FirebaseStorage.getInstance().getReference(thumbnailPath!!)

    GlideApp.with(context)
            .load(originalRef)
            .apply(RequestOptions().format(DecodeFormat.DEFAULT))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .error(
                    GlideApp.with(context)
                            .load(thumbnailRef)
                            .placeholder(errorImageDrawable)
                            .error(errorImageDrawable)
            )
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .into(this)
}
}

fun ImageView.loadMediaAttachmentVerticalPreview(attachment: Attachment,
                                                 placeHolder: Int = R.color.colorPrimary,
                                                 errorImage: Int = R.color.colorPrimary,
                                                 errorVideo: Int = R.color.colorPrimary,
                                                 onLoadDone: (() -> Unit)? = null,
                                                 onLoadFailed: (() -> Unit)? = null) {

    when {
        attachment.isImage -> {
            if (attachment.isSvg) {
                this.scaleType = ImageView.ScaleType.CENTER_INSIDE
                this.loadImageAttachment(attachment.thumbnailPath, attachment.originalPath, 0, errorImage, true)
            } else {
                this.scaleType = ImageView.ScaleType.FIT_XY
                this.loadImageAttachment(attachment.thumbnailPath, attachment.originalPath, placeHolder, errorImage)
            }
        }
        attachment.isVideo -> {
            this.scaleType = ImageView.ScaleType.FIT_XY
            this.loadVideoAttachment(attachment, placeHolder, errorVideo, onLoadDone, onLoadFailed)
        }
    }
}

fun Drawable.getPadding(): Rect {
    val rect = Rect()
    this.getPadding(rect)
    return rect
}

private fun <TranscodeType> GlideRequest<TranscodeType>.signature(signature: IntegerVersionSignature?): GlideRequest<TranscodeType> {
    return if (signature == null) {
        this
    } else {
        this.signature(signature)
    }
}*/
