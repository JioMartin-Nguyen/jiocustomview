package jio.customview.app.presentation.activity.database

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager : SQLiteOpenHelper {

    companion object {
        const val DATABASE_NAME = "STUDENT_MANAGER"
        const val TABLE_NAME = "STUDENT_TABLE"
        const val ID = "id"
        const val NAME = "name"
        const val EMAIL = "email"
        const val TELEPHONE = "telephone"
        const val ADRESS = "adress"
        const val version = 1
    }

    constructor(context: Context) : super(context, DATABASE_NAME, null, version)

    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}