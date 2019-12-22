package com.example.app2

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY " +
                "AUTOINCREMENT,TITLE TEXT,DESCRIPTION TEXT,RATING TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun insertData(title: String, description: String, rating: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, title)
        contentValues.put(COL_3, description)
        contentValues.put(COL_4, rating)
        db.insert(TABLE_NAME, null, contentValues)
    }

    fun updateData(id: String, title: String, description: String, rating: String):
            Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, title)
        contentValues.put(COL_3, description)
        contentValues.put(COL_4, rating)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    fun deleteData(id : String) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"ID = ?", arrayOf(id))
    }

    val allData : Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
            return res
        }

    companion object {
        val DATABASE_NAME = "tvseries.db"
        val TABLE_NAME = "tvseries_table"
        val COL_1 = "ID"
        val COL_2 = "TITLE"
        val COL_3 = "DESCRIPTION"
        val COL_4 = "RATING"
    }
}
//end