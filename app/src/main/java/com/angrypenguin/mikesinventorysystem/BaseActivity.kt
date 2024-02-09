package com.angrypenguin.mikesinventorysystem

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.angrypenguin.mikesinventorysystem.contracts.DBHelper

open class BaseActivity : ComponentActivity() {
    var db: SQLiteDatabase? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbHelper = DBHelper(this)
        db = dbHelper!!.writableDatabase
    }

    override fun onDestroy() {
        super.onDestroy()

        db?.close()
    }
}