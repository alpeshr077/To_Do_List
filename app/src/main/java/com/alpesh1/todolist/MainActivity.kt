package com.alpesh1.todolist

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpesh1.todolist.Adapter.TaskAdapter
import com.alpesh1.todolist.DataBase.DBHelper
import com.alpesh1.todolist.ModelClass.TaskModel
import com.alpesh1.todolist.databinding.ActivityMainBinding
import com.alpesh1.todolist.databinding.UpdateDataBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DBHelper
    lateinit var adapter: TaskAdapter
    var tasklist = ArrayList<TaskModel>()

    var category = arrayOf("Time Management","Calling","Work","Personal","Shopping","Wishlist")
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

//        tasklist = tasklist.reversed().asReversed() as ArrayList<TaskModel>

        tasklist = dbHelper.gettask()

        binding.addbtn.setOnClickListener {
            var intent = Intent(this,new_task_add::class.java)
            startActivity(intent)
        }

        adapter = TaskAdapter({
            updateDialog(it)
        }, {
            deleteTask(it)
        })

        adapter.settask(tasklist)

        binding.recyclerTask.layoutManager = LinearLayoutManager(this)
        binding.recyclerTask.adapter = adapter

    }

    private fun deleteTask(it: Int) {
        var dialog = AlertDialog.Builder(this)
            .setTitle("Delete Task")
            .setMessage("Are you sure you want delete task")
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {

                    dbHelper.deleteTask(it)

                        adapter.updateData(
                            dbHelper.gettask().reversed() as java.util.ArrayList<TaskModel>)
                }

            }).setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {

                }

            }).create()
        dialog.show()
        dialog.dismiss()

    }

    private fun updateDialog(taskModel: TaskModel) {
        var dialog = Dialog(this)
        var bind = UpdateDataBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)

        bind.addtask.setText(taskModel.addtask.toString())
        bind.edtsetDate.setText(taskModel.date.toString())
        bind.edtsetDate.setText(taskModel.month.toString())
        bind.edtsetDate.setText(taskModel.year.toString())
        bind.edtsetTime.setText(taskModel.minute.toString())
        bind.edtsetTime.setText(taskModel.hour.toString())

        bind.btnSubmit.setOnClickListener {

            var addtask = bind.addtask.text.toString()
            var date = bind.edtsetDate.text.toString()
            var month = bind.edtsetDate.text.toString()
            var year = bind.edtsetDate.text.toString()
            var minute = bind.edtsetTime.text.toString()
            var hour = bind.edtsetTime.text.toString()

            val model = TaskModel(taskModel.id, addtask, date, month, year, minute, hour)

            dbHelper.updateTask(model)
            dialog.dismiss()
            adapter.updateData(dbHelper.gettask().reversed() as java.util.ArrayList<TaskModel>)

            bind.addtask.setText("")
            bind.edtsetDate.setText("")
            bind.edtsetTime.setText("")

        }

        dialog.show()
    }

}