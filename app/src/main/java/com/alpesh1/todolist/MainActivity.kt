package com.alpesh1.todolist

import android.annotation.SuppressLint
import android.content.Intent
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpesh1.todolist.Adapter.TaskAdapter
import com.alpesh1.todolist.DataBase.DBHelper
import com.alpesh1.todolist.ModelClass.TaskModel
import com.alpesh1.todolist.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DBHelper
    lateinit var adapter:TaskAdapter
    var tasklist = ArrayList<TaskModel>()

    var category = arrayOf("Time Management","Calling","Work","Personal","Shopping","Wishlist")
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        binding.recyclerTask.layoutManager = LinearLayoutManager(this)
        binding.recyclerTask.adapter = adapter


        binding.addbtn.setOnClickListener {
            var  intent=Intent(this,new_task_add::class.java)
            startActivity(intent)
        }

    }

}