package com.alpesh1.todolist.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alpesh1.todolist.ModelClass.TaskModel
import com.alpesh1.todolist.databinding.StoreDataItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    lateinit var context: Context

    var tasklist = ArrayList<TaskModel>()

    class TaskHolder(itemView: StoreDataItemBinding) : ViewHolder(itemView.root){
        val binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        context = parent.context
        var binding = StoreDataItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasklist.size
    }

    override fun onBindViewHolder(holder: TaskHolder, @SuppressLint("RecyclerView") position: Int) {
       holder.binding.apply {
           tasklist.get(position).apply {
               setData.text = addtask
           }
       }
    }

    fun settask(tasklist:ArrayList<TaskModel>) {
        this.tasklist = tasklist
    }


}