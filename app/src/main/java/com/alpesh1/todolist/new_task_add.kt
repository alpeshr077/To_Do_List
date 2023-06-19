package com.alpesh1.todolist

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.core.content.ContentProviderCompat.requireContext
import com.alpesh1.todolist.databinding.ActivityMainBinding
import com.alpesh1.todolist.databinding.ActivityNewTaskAddBinding
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class new_task_add : AppCompatActivity() {

    lateinit var binding: ActivityNewTaskAddBinding
    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edtSetDate.setOnClickListener {

            val c = Calendar.getInstance()


            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(

                this,
                { view, year, monthOfYear, dayOfMonth ->

                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.edtSetDate.setText(dat)
                },

                year,
                month,
                day
            )

            datePickerDialog.show()
        }
        binding.BackArrow.setOnClickListener {
            var  intent=Intent(this,MainActivity::class.java)
            startActivity(intent)

        }


    }
}