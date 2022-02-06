package com.sosnowskydevelop.studyguides.data

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.studyguides.utilities.DatabaseHelper
import java.io.IOException

@SuppressLint("Range")
class SubcategoriesRepository(context: Context) {
    private var subcategories: Array<Subcategory> = arrayOf()

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

        val cursor: Cursor = database.query(
            "Subcategory",
            arrayOf(
                "_id",
                "name",
                "categoryId",
            ),
            null, null, null, null, null
        )

        var isEntryNotEmpty: Boolean = cursor.moveToFirst()
        while (isEntryNotEmpty) {
            subcategories += Subcategory(
                _id = cursor.getInt(cursor.getColumnIndex("_id")),
                name = cursor.getString(cursor.getColumnIndex("name")),
                categoryId = cursor.getInt(cursor.getColumnIndex("categoryId")),
            )
            isEntryNotEmpty = cursor.moveToNext()
        }
        cursor.close()
    }

    fun getSubcategory(subcategoryId: Int): Subcategory = subcategories[subcategoryId - 1]

    fun getSubcategories(categoryId: Int): Array<Subcategory> {
        var result: Array<Subcategory> = arrayOf()

        subcategories.forEach { subcategory ->
            if (subcategory.categoryId == categoryId) {
                result += subcategory
            }
        }

        return result
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: SubcategoriesRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: SubcategoriesRepository(context = context).also { instance = it }
            }
    }
}