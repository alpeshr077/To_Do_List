package com.alpesh1.todolist.ModelClass

class TaskModel {

    var id = 0
    var addtask = ""
    var date = ""
    var month = ""
    var year = ""
    var minute = ""
    var hour = ""

    constructor(
        id: Int,
        addtask: String,
        date: String,
        month: String,
        year: String,
        minute: String,
        hour: String
    ) {
        this.id = id
        this.addtask = addtask
        this.date = date
        this.month = month
        this.year = year
        this.minute = minute
        this.hour = hour
    }
}
