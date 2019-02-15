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
import jio.customview.app.domain.model.modelstandard.ActionState
import jio.customview.app.model.Food
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

        // exampleRxJava()
        // exampleSequence()
        exampleTest()
    }

    @OnClick(R.id.btn_cache_img)
    fun clickCacheImage(){
        goToTestCheckCacheImage()
    }

    @OnClick(R.id.btn_cache_img_2)
    fun clickCacheImage2(){
        goToTestCheckCacheImage2()
    }

    private fun goToTestCheckCacheImage() {
        startActivity(CacheImageActivity.createIntent(this));
    }

    private fun goToTestCheckCacheImage2() {
        startActivity(CacheImageActivity.createIntent(this));
    }

    private fun exampleTest() {

        System.out.println(ActionState.ACTIVE.name)
        System.out.println(ActionState.INACTIVE.name)

        System.out.println(ActionState.ACTIVE.reverseAction())
        System.out.println(ActionState.INACTIVE.reverseAction())
    }

    private fun exampleSequence() {
        val seq = sequenceOf(1, 2, 3)
        println(seq.filter { it % 2 == 1 })   // <- Prints: kotlin.sequences.FilteringSequence@XXXXXXXX
        println(seq.filter { it % 2 == 1 }.toList()) // Prints: [1,3]

        val list = listOf<Int>(1, 2, 3)
        println(list.filter { it % 2 == 1 }) // Prints: [1,3]

        var mang = arrayOf(1, 2, 3, 4)
        val sequenceTest = mang.iterator().asSequence()
        // println(sequenceTest.joinToString())
        println(sequenceTest
                .filter {
                    it % 2 == 0
                }
                .map {
                    it * 2
                }
                .drop(1)
                .joinToString())

    }

    @OnClick(R.id.btn_test_recycle_view)
    fun openExampleRecycleView() {
        startActivity(Intent(this, JioRecycleViewActivity::class.java))
    }

    @OnClick(R.id.btn_play_video)
    fun playVideo(){
        startActivity(Intent(this, PlayVideoActivity::class.java))
    }

    private fun exampleRxJava() {

        val myObservable =
                Observable
                        .from(arrayOf(1, 2, 3))
                        .subscribe {
                            System.out.println(it)
                        }

        val myObservableJust = Observable.just(arrayOf(1, 2, 3))
                .subscribe {
                    System.out.println("$it AAA")
                }
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
