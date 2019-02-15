package jio.customview.app.domain.model.modelstandard.common

import android.os.Parcel
import android.os.Parcelable
import jio.customview.app.common.constant.ConstantFirebaseDb
import jio.customview.app.common.utils.ImageUtils
import java.util.*

open class Attachment() : Parcelable, Cloneable {


    companion object CREATOR : Parcelable.Creator<Attachment> {

        const val KEY_METADATA_NAME = "name"
        const val KEY_METADATA_WIDTH = "width"
        const val KEY_METADATA_HEIGHT = "height"

        override fun createFromParcel(parcel: Parcel): Attachment {
            return Attachment(parcel)
        }

        override fun newArray(size: Int): Array<Attachment?> {
            return arrayOfNulls(size)
        }


        /**
         * AP3003, AP3004, AP3005
         */
        fun comparator(p0: Attachment, p1: Attachment): Int {
            if (p0.isImage && !p1.isImage) return -1
            if (!p0.isImage && p1.isImage) return 1

            if (p0.isVideo && !p1.isVideo) return -1
            if (!p0.isVideo && p1.isVideo) return 1

            if (p0.created == null && p1.created == null) return 0
            if (p0.created == null && p1.created != null) return -1
            if (p0.created != null && p1.created == null) return 1

            if (p0.created!! < p1.created!!) return -1
            if (p0.created!! > p1.created!!) return 1

            return 0
        }

        /**
         * AP7001
         */
        fun comparatorAttachment(p0: Attachment, p1: Attachment): Int {
            if (p0.created!! < p1.created!!) return 1
            if (p0.created!! > p1.created!!) return -1
            return 0
        }

    }

    var id: String? = null

    var fileId: String? = null

    var created: Date? = null

    var creator: String? = null

    var url: String? = null

    var name: String? = null

    var size: Long = 0

    var mimeType: String? = null

    var width: Int? = null

    var height: Int? = null

    var isError: Boolean = false

    var originalPath: String? = null

    var thumbnailPath: String? = null

    val isImage: Boolean
        get() {
            return if (mimeType == null) {
                false
            } else {
                mimeType!! in ImageUtils.supportedImageExtension
            }
        }

    val isVideo: Boolean
        get() {
            return mimeType?.contains(ConstantFirebaseDb.VIDEO) ?: false
        }

    val isUploaded: Boolean
        get() {
            return fileId != null
        }

    val hasMetadata: Boolean
        get() {
            return if (isUploaded) {
                name != null
            } else {
                url != null
            }
        }

    val isSvg: Boolean
        get() {
            return mimeType?.contains(ConstantFirebaseDb.SVG) ?: false
        }

    constructor(fileId: String) : this() {
        this.fileId = fileId
    }

    constructor(id: String, fileId: String) : this() {
        this.id = id
        this.fileId = fileId
    }

    constructor(parcel: Parcel) : this() {
        readParcel(parcel)
    }

    protected fun readParcel(parcel: Parcel) {
        id = parcel.readString()
        fileId = parcel.readString()
        created = parcel.readSerializable() as Date?
        creator = parcel.readString()
        url = parcel.readString()
        name = parcel.readString()
        size = parcel.readLong()
        mimeType = parcel.readString()
        width = parcel.readValue(Int::class.java.classLoader) as Int?
        height = parcel.readValue(Int::class.java.classLoader) as Int?
        isError = parcel.readValue(Boolean::class.java.classLoader) as Boolean
        originalPath = parcel.readString()
        thumbnailPath = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(fileId)
        parcel.writeSerializable(created)
        parcel.writeString(creator)
        parcel.writeString(url)
        parcel.writeString(name)
        parcel.writeLong(size)
        parcel.writeString(mimeType)
        parcel.writeValue(width)
        parcel.writeValue(height)
        parcel.writeValue(isError)
        parcel.writeString(originalPath)
        parcel.writeString(thumbnailPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (other is Attachment) {
            if (fileId != other.fileId) {
                return false
            }

            return true
        }

        return false
    }

    override fun hashCode(): Int {
        return fileId?.hashCode() ?: 0
    }

    fun getMimeTypeChat(): String {
        return when {
            isImage -> ConstantFirebaseDb.IMAGE
            isVideo -> ConstantFirebaseDb.VIDEO
            else -> mimeType ?: ""
        }
    }
}