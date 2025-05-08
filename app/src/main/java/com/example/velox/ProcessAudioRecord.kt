package com.example.velox

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class ProcessAudioRecord : AppCompatActivity() {

    private val client = OkHttpClient()
    private lateinit var responseTextView: TextView
    private lateinit var recognizedTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process_audio_record)

        // Получаем ссылки один раз
        recognizedTextView = findViewById(R.id.textView)
        responseTextView = findViewById(R.id.responce)

        // Показываем распознанный текст
        val recognizedText = intent.getStringExtra("recognized_text")
        recognizedTextView.text = recognizedText

        sendPostRequestToBackend(recognizedText)
    }

    private fun sendPostRequestToBackend(txtRecognized: String?) {
        val requestJson = JSONObject().apply {
            put("text", txtRecognized)
        }

        val requestBody = requestJson.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaType())

        val request = Request.Builder()
            .url("http://10.0.2.2:5000/")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                updateResponse("Error: ${e.localizedMessage}")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val result = if (!response.isSuccessful || body.isNullOrBlank()) {
                    "Answer error: ${response.code}"
                } else {
                    try {
                        val json = JSONObject(body)
                        val task = Task(
                            title = json.getString("title"),
                            date = json.getString("date"),
                            time_start = json.optString("time_start", null),
                            time_end = json.optString("time_end", null)
                        )
                        val intent = Intent(this@ProcessAudioRecord, CreatedTaskAI::class.java)
                        intent.putExtra("task", task)
                        startActivity(intent)

                        task.toString()

                    } catch (e: Exception) {
                        "Ошибка при разборе ответа: ${e.localizedMessage}"
                    }
                }

                updateResponse(result)
            }
        })
    }

    private fun updateResponse(message: String) {
        runOnUiThread {
            responseTextView.text = message
        }
    }


}