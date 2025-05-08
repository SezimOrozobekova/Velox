package com.example.velox

data class Task(
//    val name: String,
//    val date: String,
//    val time: String,
//    val type:
    val title: String,
    val date: String,
    val time_start: String?,
    val time_end: String?,
) : java.io.Serializable
