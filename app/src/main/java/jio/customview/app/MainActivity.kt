package jio.customview.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {

    @BindView(R.id.btn_test_recycle_view)
    lateinit var btnTestRecycleView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        btnTestRecycleView.setText("sdads")
    }

    @OnClick(R.id.btn_test_recycle_view)
    fun openExampleRecycleView() {
        startActivity(Intent(this, JioRecycleViewActivity::class.java))
    }
}
