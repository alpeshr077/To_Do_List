package com.alpesh1.todolist.ModelClass

class TaskModel {

    var id = 0
    var addtask = ""
    var date = "DD-MM-YYYY"
    var time = "HOUSE:MINUTE"

    constructor(
        id: Int,
        addtask: String,
        date: String,
        time: String
    ) {
        this.id = id
        this.addtask = addtask
        this.date = date
        this.time = time
    }
}
