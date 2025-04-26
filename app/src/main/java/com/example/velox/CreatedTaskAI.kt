package com.example.velox

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CreatedTaskAI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_created_task_ai)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recycleView = findViewById<RecyclerView>(R.id.rvAiCreatedTask)
        recycleView.layoutManager = LinearLayoutManager(this)
        // LinearLayoutManager ставит элементы линейно
        // А this — это значит "используй текущий Context", то есть Activity или Fragment, где ты сейчас находишьс

        // 1. Список задач
        val taskList = listOf(
            Task("Read Book", "Tuesday", "8:15 - 9:15 (1h 0m)", "Hobby"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Go Gym", "Wednesday", "10:00 - 11:00 (1h 0m)", "Sport"),
            Task("Write Code", "Thursday", "12:00 - 14:00 (2h 0m)", "Work")
        )

        // 2. Подключаем адаптер
        recycleView.adapter = AdapterCreatedTaskAI(taskList)
    }
}