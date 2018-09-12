package jio.customview.app.presentation.view

interface TallyCounter {

    fun reset()

    fun increment()

    fun getCount():Int

    fun setCount(count: Int)
}