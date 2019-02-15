package jio.customview.app.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import jio.customview.app.R

class CacheImageActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            val intent = Intent(context, CacheImageActivity::class.java)
            return intent;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache_image)
    }
}
