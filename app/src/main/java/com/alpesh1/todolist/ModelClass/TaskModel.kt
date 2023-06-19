package com.alpesh1.todolist.ModelClass

class TaskModel {

    var id = 0
    var addtask = ""
    var date =""
    var month = ""
    var year = ""

    constructor(id: Int, addtask: String, date: String, month: String, year: String) {
        this.id = id
        this.addtask = addtask
        this.date = date.toString()
        this.month = month.toString()
        this.year = year.toString()
    }
}
