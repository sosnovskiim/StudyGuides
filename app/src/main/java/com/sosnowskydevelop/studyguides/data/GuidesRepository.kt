package com.sosnowskydevelop.studyguides.data

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.studyguides.utilities.DatabaseHelper
import java.io.IOException

@SuppressLint("Range")
class GuidesRepository(context: Context) {
    private var guides: Array<Guide> = arrayOf()

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

    fun getGuide(guideId: Int): Guide? {
        database.rawQuery(
            "SELECT * FROM Main WHERE _id = $guideId",
            null,
        ).use { cursor ->
            if (cursor.moveToFirst()) {
                return Guide(
                    id = cursor.getInt(cursor.getColumnIndex("_id")),
                    parentId = cursor.getInt(cursor.getColumnIndex("parentId")),
                    name = cursor.getString(cursor.getColumnIndex("name")),
                    value = cursor.getString(cursor.getColumnIndex("value")),
                    type = cursor.getString(cursor.getColumnIndex("type")),
                )
            }

            return null
        }
    }

    fun getGuides(parentId: Int): Array<Guide> {
        database.rawQuery(
            "SELECT * FROM Main WHERE parentId = $parentId ORDER BY name",
            null,
        ).use { cursor ->
            var result: Array<Guide> = arrayOf()

            if (cursor.moveToFirst()) {
                do {
                    result += Guide(
                        id = cursor.getInt(cursor.getColumnIndex("_id")),
                        parentId = cursor.getInt(cursor.getColumnIndex("parentId")),
                        name = cursor.getString(cursor.getColumnIndex("name")),
                        value = cursor.getString(cursor.getColumnIndex("value")),
                        type = cursor.getString(cursor.getColumnIndex("type")),
                    )
                } while (cursor.moveToNext())
            }

            return result
        }
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: GuidesRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: GuidesRepository(context = context).also { instance = it }
            }
    }
}