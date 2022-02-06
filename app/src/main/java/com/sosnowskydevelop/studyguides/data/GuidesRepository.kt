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

        val cursor: Cursor = database.query(
            "Guide",
            arrayOf(
                "_id",
                "name",
                "value",
                "type",
                "subcategoryId",
            ),
            null, null, null, null, null
        )

        var isEntryNotEmpty: Boolean = cursor.moveToFirst()
        while (isEntryNotEmpty) {
            guides += Guide(
                _id = cursor.getInt(cursor.getColumnIndex("_id")),
                name = cursor.getString(cursor.getColumnIndex("name")),
                value = cursor.getString(cursor.getColumnIndex("value")),
                type = cursor.getString(cursor.getColumnIndex("type")),
                subcategoryId = cursor.getInt(cursor.getColumnIndex("subcategoryId")),
            )
            isEntryNotEmpty = cursor.moveToNext()
        }
        cursor.close()
    }

    fun getGuide(guideId: Int): Guide = guides[guideId - 1]

    fun getGuides(subcategoryId: Int): Array<Guide> {
        var result: Array<Guide> = arrayOf()

        guides.forEach { guide ->
            if (guide.subcategoryId == subcategoryId) {
                result += guide
            }
        }

        result.sortBy { it.name }

        return result
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