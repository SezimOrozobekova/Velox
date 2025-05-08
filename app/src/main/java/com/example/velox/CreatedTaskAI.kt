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

        val task = intent.getSerializableExtra("task") as? Task
        if (task != null) {
            val taskList = listOf(task)
            recycleView.adapter = AdapterCreatedTaskAI(taskList)
        } else {
            recycleView.adapter = AdapterCreatedTaskAI(emptyList())
        }

    }
}