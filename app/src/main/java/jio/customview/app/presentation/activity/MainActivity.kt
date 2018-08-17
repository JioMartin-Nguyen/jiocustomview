package jio.customview.app.presentation.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import jio.customview.app.R
import rx.Observable
import rx.Subscriber

class MainActivity : AppCompatActivity() {

    @BindView(R.id.btn_test_recycle_view)
    lateinit var btnTestRecycleView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        btnTestRecycleView.text = "sdads"

        exampleRxJava()
    }

    @OnClick(R.id.btn_test_recycle_view)
    fun openExampleRecycleView() {
        startActivity(Intent(this, JioRecycleViewActivity::class.java))
    }

    private fun exampleRxJava() {

    }

    fun getDetail(): Int {
        return 100
    }

    var observable = Observable
            .from(arrayOf(1, 2, 3))
            .subscribe(object : Subscriber<Int>() {
                override fun onNext(number: Int) {
                    Log.e("=====>onNext", number.toString())
                }

                override fun onCompleted() {
                    Log.e("==>observable:", "Complete")
                }

                override fun onError(e: Throwable) {
                    Log.e("==>observable:", "onError")
                }
            })


    data class Student(var name: String, var tuoi: Int)
}
