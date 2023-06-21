package com.alpesh1.todolist.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alpesh1.todolist.ModelClass.TaskModel
import com.alpesh1.todolist.R
import com.alpesh1.todolist.databinding.StoreDataItemBinding

class TaskAdapter(update:(TaskModel) -> Unit,delete:(Int) -> Unit) : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    var update = update
    var delete = delete

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
               setDate.text = date
               setDate.text = month
               setDate.text = year
               setTime.text = minute
               setTime.text = hour
           }
       }

        holder.itemView.setOnLongClickListener(object : OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {

                var popupMenu = PopupMenu(context,holder.itemView)
                popupMenu.menuInflater.inflate(R.menu.menu1,popupMenu.menu)

                popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
                    override fun onMenuItemClick(p0: MenuItem?): Boolean {

                        if (p0?.itemId==R.id.edit){

                            update.invoke(tasklist.get(position))
                        }
                        if (p0?.itemId==R.id.delete){

                            delete.invoke(tasklist.get(position).id)
                        }

                        return true
                    }

                } )

                popupMenu.show()

                return true

            }

        })

    }

    fun settask(tasklist:ArrayList<TaskModel>) {
        this.tasklist = tasklist
    }

    fun updateData(gettask:ArrayList<TaskModel>) {
        tasklist = gettask
        notifyDataSetChanged()
    }

}