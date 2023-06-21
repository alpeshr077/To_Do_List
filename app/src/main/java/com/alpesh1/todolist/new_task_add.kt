package com.alpesh1.todolist

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alpesh1.todolist.DataBase.DBHelper
import com.alpesh1.todolist.ModelClass.TaskModel
import com.alpesh1.todolist.databinding.ActivityNewTaskAddBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class new_task_add : AppCompatActivity() {

    lateinit var binding: ActivityNewTaskAddBinding

    lateinit var dbHelper: DBHelper

    lateinit var taskModel: TaskModel
    lateinit var notificationManager: NotificationManager
    lateinit var pendingIntent: PendingIntent
    var CHANNEL_ID="my Channel"
    var Notification_ID=100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        binding.edtsetDate.setOnClickListener {

            var date = Date()

            var format1 = SimpleDateFormat("dd-MM-YYYY")
            var currentDate = format1.format(date)

            var dates = currentDate.split("-")
            binding.edtsetDate.setText(currentDate)

            var dialog =
                DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {



                        var selectedDate = "$p3-${(p2 + 1)}-$p1"
                        binding.edtsetDate.setText(selectedDate)
                    }

                }, dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
            dialog.show()
        }

        binding.edtsetTime.setOnClickListener {
            var date = Date()

            var format2 = SimpleDateFormat("hh:mm a")
            var currentTime = format2.format(date)

            binding.edtsetTime.setText(currentTime)
            var seleTime = currentTime




            var dialog1 = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int){


                    var selectedTime = "$p1:$p2"
                    binding.edtsetTime.setText(selectedTime)
                }

            }, 10, 0, true)
            dialog1.show()
        }


//        binding.edtSetDate.setOnClickListener {
//
//            val c = Calendar.getInstance()
//
//
//            val year = c.get(Calendar.YEAR)
//            val month = c.get(Calendar.MONTH)
//            val day = c.get(Calendar.DAY_OF_MONTH)
//
//
//            val datePickerDialog = DatePickerDialog(
//
//                this,
//                { view, year, monthOfYear, dayOfMonth ->
//
//                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
//                    binding.edtSetDate.setText(dat)
//                },
//
//                year,
//                month,
//                day
//            )
//
//            datePickerDialog.show()
//        }
        binding.BackArrow.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        binding.btnSubmit.setOnClickListener {

            var addtask = binding.addtask.text.toString()
            var date = binding.edtsetDate.text.toString()
            var month = binding.edtsetDate.text.toString()
            var year = binding.edtsetDate.text.toString()
            var minute = binding.edtsetTime.text.toString()
            var hour = binding.edtsetTime.text.toString()

            val model = TaskModel(1, addtask, date, month, year, minute, hour)

            dbHelper.addTask(model)

            binding.addtask.setText("")
            binding.edtsetDate.setText("")
            binding.edtsetTime.setText("")



        }

    }



 }