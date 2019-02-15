package jio.customview.app.presentation.activity.database

import android.os.Bundle
import android.os.PersistableBundle
import jio.customview.app.R
import jio.customview.app.presentation.activity.BaseActivity

class ExampleSqlLiteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_example_sql_lite)
    }

}