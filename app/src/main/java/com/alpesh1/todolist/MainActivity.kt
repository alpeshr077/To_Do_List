package com.alpesh1.todolist

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpesh1.todolist.Adapter.TaskAdapter
import com.alpesh1.todolist.DataBase.DBHelper
import com.alpesh1.todolist.ModelClass.TaskModel
import com.alpesh1.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DBHelper
    lateinit var adapter:TaskAdapter
    var tasklist = ArrayList<TaskModel>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val menu = null
        menuInflater.inflate(R.menu.menu1,menu)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dbHelper = DBHelper(this)
        adapter = TaskAdapter()
        tasklist = dbHelper.gettask()

        binding.addbutton.setOnClickListener {
            var intent = Intent(this,new_task_add::class.java)
            startActivity(intent)
        }

        adapter.settask(tasklist)
        adapter.updatetask(tasklist)

        binding.recyclerTask.layoutManager = LinearLayoutManager(this)
        binding.recyclerTask.adapter = adapter

        binding.optionMenu.setOnClickListener {


        }


    }

}