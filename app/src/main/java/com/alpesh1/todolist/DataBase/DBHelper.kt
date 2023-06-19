package com.alpesh1.todolist.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.alpesh1.todolist.ModelClass.TaskModel

class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Database", null, 1) {

    var TABLE_NAME = "Task"
    var ID = "id"
    var ADDTASK = "addtask"
    var DATE = "date"
    var MONTH = "month"
    var YEAR = "year"


    override fun onCreate(p0: SQLiteDatabase?) {

        var que = "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT,$ADDTASK TEXT,$DATE TEXT, $MONTH TEXT, $YEAR TEXT)"

        p0?.execSQL(que)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addTask(taskModel: TaskModel){

        var db = writableDatabase
        var values = ContentValues().apply {
            taskModel.apply {
                put(ID,id)
                put(ADDTASK,addtask)
                put(DATE,date)
                put(MONTH,month)
                put(YEAR,year)
            }
        }
        db.insert(TABLE_NAME,null,values)
    }

    fun gettask(): ArrayList<TaskModel> {
        var tasklist = ArrayList<TaskModel>()
        var db = readableDatabase
        var que = "SELECT * FROM $TABLE_NAME"
        var cursor = db.rawQuery(que,null)
        cursor.moveToFirst()

        for (i in 0 ..cursor.count-1){
            var id = cursor.getInt(0)
            var addtask = cursor.getString(1)
            var date = cursor.getInt(2)
            var month = cursor.getInt(3)
            var year = cursor.getInt(4)

            var model = TaskModel(id,addtask,date,month,year)

            tasklist.add(model)
            cursor.moveToNext()
        }
        return tasklist
    }

}