package com.kevin.dataenter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.kevin.dataenter.Model.StudenModel

class DbHelper(
    context: Context?,
) : SQLiteOpenHelper(context, "Data1.db", null, 1) {
    private val TAG = "DbHelper"
    val TABLE_NAME = "student"

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql =
            "CREATE TABLE $TABLE_NAME(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, std INTEGER)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun addStudent(stdData: StudenModel) {
        var db = writableDatabase
        var values = ContentValues().apply {
            put("name", stdData.name)
            put("surname", stdData.surname)
            put("std", stdData.std)
        }
        var iss = db.insert(TABLE_NAME, null, values)
        if (iss.toInt() == -1) {
            Log.e(TAG, "addStudents: ===================== Data is Not Inserted")
        } else {
            Log.e(TAG, "addStudents: ===================== Data is Inserted ================")
        }
    }

    fun updateStudent(studenModel: StudenModel) {
        var db = writableDatabase
        var sql = "UPDATE $TABLE_NAME SET name='${studenModel.name}', surname='${studenModel.surname}' , std='${studenModel.std}' WHERE ID='${studenModel.id}'"

        Log.e(TAG, "updatestudent: Query ====== $sql")
        var values = ContentValues().apply {
            put("name", studenModel.name)
            put("surname", studenModel.surname)
            put("std", studenModel.std)
        }

        var iss = db.update(TABLE_NAME, values, "id=${studenModel.id}",null)
        if (iss.toInt() == -1) {
            Log.e(TAG, "updatestudent: ========== Data is not update")
        } else {
            Log.e(TAG, "updatestudent: ========== Data updated ==========")
        }
    }

    fun getStudents(): ArrayList<StudenModel> {
        var studentList = ArrayList<StudenModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM $TABLE_NAME"
        var cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()

        for (i in 0..cursor.count - 1) {
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var surname = cursor.getString(2)
            var std = cursor.getString(3)
            var model = StudenModel(id, name, surname, std)
            studentList.add(model)
            cursor.moveToNext()
        }
        return studentList
    }

    fun deleteStudent(id: Int) {
        var db = writableDatabase
        db.delete(TABLE_NAME, "id=$id", null)
    }
}