package jio.customview.app.common.imageloader

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class MyCustomGlide : AppGlideModule() {

   /* companion object {
        const val MEMORY_CACHE_SIZE = 1024 * 1024 * 1L
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, MEMORY_CACHE_SIZE))
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        *//*registry.append(StorageReference::class.java, InputStream::class.java, FirebaseImageLoader.Factory())
        registry.append(ImagePath::class.java, InputStream::class.java, AttachmentImageLoader.Factory())
        registry.register(SVG::class.java, PictureDrawable::class.java, SvgDecoder.SvgDrawableTranscoder())
                .append(InputStream::class.java, SVG::class.java, SvgDecoder())*//*

    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }*/
}