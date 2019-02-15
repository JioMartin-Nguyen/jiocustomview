package jio.customview.app.presentation.widget

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * AP7021-Fix error lib PhotoView for ViewPager
 * It's the android ViewPager's bug
 * You can extends the ViewPager class,
 * your own ViewPager should override the onTouchEvent and the onInterceptTouchEvent methods,
 * and try-catch the IllegalArgumentException exception.
 * Then use your own ViewPager class in layout or others you want
 */
class HackyProblematicViewPager : ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        try {
            return super.onTouchEvent(ev)
        } catch (e: IllegalArgumentException) {
            //Comment if you don't really want to see these errors
            e.printStackTrace()
        }
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        try {
            return super.onInterceptTouchEvent(ev)
        } catch (e: IllegalArgumentException) {
            //Comment if you don't really want to see these errors
            e.printStackTrace()
        }
        return false
    }
}