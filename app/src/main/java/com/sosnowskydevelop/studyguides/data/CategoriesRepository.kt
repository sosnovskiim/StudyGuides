package com.sosnowskydevelop.studyguides.data

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.studyguides.utilities.DatabaseHelper
import java.io.IOException

@SuppressLint("Range")
class CategoriesRepository(context: Context) {
    private var categories: Array<Category> = arrayOf()

    init {
        val databaseHelper = DatabaseHelper(context)
        val database: SQLiteDatabase

        try {
            databaseHelper.updateDataBase()
        } catch (mIOException: IOException) {
            throw Error("UnableToUpdateDatabase")
        }

        try {
            database = databaseHelper.readableDatabase
        } catch (mSQLException: SQLException) {
            throw mSQLException
        }

        val cursor: Cursor = database.query(
            "Category",
            arrayOf(
                "_id",
                "name",
            ),
            null, null, null, null, null
        )

        var isEntryNotEmpty: Boolean = cursor.moveToFirst()
        while (isEntryNotEmpty) {
            categories += Category(
                _id = cursor.getInt(cursor.getColumnIndex("_id")),
                name = cursor.getString(cursor.getColumnIndex("name")),
            )
            isEntryNotEmpty = cursor.moveToNext()
        }
        cursor.close()
    }

    fun getCategory(categoryId: Int): Category = categories[categoryId - 1]

    fun getCategories(): Array<Category> = categories

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: CategoriesRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CategoriesRepository(context = context).also { instance = it }
            }
    }
}