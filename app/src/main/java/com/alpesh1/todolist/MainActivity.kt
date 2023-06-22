package com.alpesh1.todolist

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpesh1.todolist.Adapter.TaskAdapter
import com.alpesh1.todolist.DataBase.DBHelper
import com.alpesh1.todolist.ModelClass.TaskModel
import com.alpesh1.todolist.databinding.ActivityMainBinding
import com.alpesh1.todolist.databinding.UpdateDataBinding
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DBHelper
    lateinit var adapter: TaskAdapter
    var tasklist = ArrayList<TaskModel>()

    var category = arrayOf("Time Management", "Calling", "Work", "Personal", "Shopping", "Wishlist")

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

//        tasklist = tasklist.reversed().asReversed() as ArrayList<TaskModel>

        tasklist = dbHelper.gettask()

        binding.addbtn.setOnClickListener {
            var intent = Intent(this, new_task_add::class.java)
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
                    adapter.updateData(dbHelper.gettask())
                }

            }).setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {

                }

            }).create()
        dialog.show()

    }

    private fun updateDialog(taskModel: TaskModel) {
        var dialog = Dialog(this)
        var bind = UpdateDataBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)

        bind.addtaskupdate.setText(taskModel.addtask.toString())
        bind.edtsetDateupdate.setText(taskModel.date.toString())
        bind.edtsetTimeupdate.setText(taskModel.time.toString())

        var date = Date()
        var format1 = SimpleDateFormat("dd-MM-YYYY")
        var currentDate = format1.format(date)

        var dates = currentDate.split("-")
        bind.edtsetDateupdate.setOnClickListener {
            var dialog =
                DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {


                        var selectedDate = "$p3-${(p2 + 1)}-$p1"
                        bind.edtsetDateupdate.setText(selectedDate)
                    }

                }, dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
            dialog.show()
        }

        bind.edtsetTimeupdate.setOnClickListener {
            var date = Date()

            var format2 = SimpleDateFormat("hh:mm a")
            var currentTime = format2.format(date)

            bind.edtsetTimeupdate.setText(currentTime)
            var selectTime = currentTime

            var dialog1 = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {


                    bind.edtsetTimeupdate.setText(selectTime)
                }

            }, 12, 60, true)
            dialog1.show()
        }
        bind.btnSubmitupdate.setOnClickListener {

            var addtask = bind.addtaskupdate.text.toString()
            var date = bind.edtsetDateupdate.text.toString()
            var time = bind.edtsetTimeupdate.text.toString()

            val model = TaskModel(taskModel.id, addtask, date, time)

            dbHelper.updateTask(model)
            adapter.updateData(dbHelper.gettask())
            dialog.dismiss()

            bind.addtaskupdate.setText("")
            bind.edtsetDateupdate.setText("")
            bind.edtsetTimeupdate.setText("")
        }

        dialog.show()
    }
}











