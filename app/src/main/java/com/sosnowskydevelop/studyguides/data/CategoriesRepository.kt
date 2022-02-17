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
    private val database: SQLiteDatabase

    init {
        val databaseHelper = DatabaseHelper(context)

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
    }

    fun getCategory(categoryId: Int): Category? {
        database.rawQuery(
            "SELECT * FROM Main WHERE _id = $categoryId",
            null,
        ).use { cursor ->
            if (cursor.moveToFirst()) {
                return Category(
                    id = cursor.getInt(cursor.getColumnIndex("_id")),
                    name = cursor.getString(cursor.getColumnIndex("name")),
                )
            }

            return null
        }
    }

    fun getCategories(): Array<Category> {
        database.rawQuery(
            "SELECT * FROM Main WHERE parentId = 0",
            null,
        ).use { cursor ->
            var result: Array<Category> = arrayOf()

            if (cursor.moveToFirst()) {
                do {
                    result += Category(
                        id = cursor.getInt(cursor.getColumnIndex("_id")),
                        name = cursor.getString(cursor.getColumnIndex("name")),
                    )
                } while (cursor.moveToNext())
            }

            return result
        }
    }

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