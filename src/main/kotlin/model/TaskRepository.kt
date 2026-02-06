package com.example.model

object TaskRepository {

    private val tasks = mutableListOf(
        Task("cleaning", "Clean the house", Priority.LOW),
        Task("gardening", "Mow the lawn", Priority.MEDIUM),
        Task("shopping", "Buy the groceries", Priority.HIGH),
        Task("painting", "Paint the fence", Priority.MEDIUM)
    )

    fun allTasks(): List<Task> = tasks

    fun tasksByPriority(priority: Priority): List<Task> = tasks.filter { it.priority == priority }

    fun taskByName(name: String): Task? = tasks.find {
        it.name.equals(name, ignoreCase = true)
    }

    fun addTask(task: Task) {
        if (taskByName(task.name) != null) {
            throw IllegalArgumentException("Task with name ${task.name} already exists")
        }

        tasks.add(task)
    }
}