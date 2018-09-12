package jio.customview.app.common.extension

import jio.customview.app.common.utils.DimensionUtill

fun Float.dpToPx(): Float {
    return DimensionUtill.dpToPx(this)
}

fun Float.pxToDp(): Float {
    return DimensionUtill.pxToDp(this)
}

fun Float.dpToSp():Float{
    return DimensionUtill.dpToSp(this)
}