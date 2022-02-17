package com.sosnowskydevelop.studyguides.data

import android.annotation.SuppressLint
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.studyguides.utilities.DatabaseHelper
import java.io.IOException

@SuppressLint("Range")
class SubcategoriesRepository(context: Context) {
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

    fun getSubcategory(subcategoryId: Int): Subcategory? {
        database.rawQuery(
            "SELECT * FROM Main WHERE _id = $subcategoryId",
            null,
        ).use { cursor ->
            if (cursor.moveToFirst()) {
                return Subcategory(
                    id = cursor.getInt(cursor.getColumnIndex("_id")),
                    parentId = cursor.getInt(cursor.getColumnIndex("parentId")),
                    name = cursor.getString(cursor.getColumnIndex("name")),
                )
            }

            return null
        }
    }

    fun getSubcategories(parentId: Int): Array<Subcategory> {
        database.rawQuery(
            "SELECT * FROM Main WHERE parentId = $parentId",
            null,
        ).use { cursor ->
            var result: Array<Subcategory> = arrayOf()

            if (cursor.moveToFirst()) {
                do {
                    result += Subcategory(
                        id = cursor.getInt(cursor.getColumnIndex("_id")),
                        parentId = cursor.getInt(cursor.getColumnIndex("parentId")),
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
        private var instance: SubcategoriesRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: SubcategoriesRepository(context = context).also { instance = it }
            }
    }
}