package com.example.getdata

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.util.*

class MainActivity2 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val name = intent.getStringExtra("name")
        val stars = intent.getIntExtra("stars",0)
        val owner = intent.getStringExtra("owner")

        val heading = findViewById<TextView>(R.id.text_heading)
        val starText = findViewById<TextView>(R.id.text_star)
        val textBox = findViewById<TextView>(R.id.text_box)

        heading.text = name
        starText.text = "Stars: $stars"

        val client = OkHttpClient()
        val url = "https://api.github.com/repos/$owner/$name/readme"
        val req = Request.Builder()
            .url(url)
            .build()

        client.newCall(req)
            .enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()
                    val feed = gson.fromJson(body, ReadmeApi::class.java)
                    val newreq = Request.Builder()
                        .url(feed.download_url)
                        .build()
                    client.newCall(newreq)
                        .enqueue(object : Callback{
                            override fun onResponse(call: Call, response: Response) {
                                val body2 = response.body?.string()
                                textBox.text = body2
                            }
                            override fun onFailure(call: Call, e: IOException) {
                                println("Failed")
                            }
                        })
                }

                override fun onFailure(call: Call, e: IOException) {
                    println("Failed")
                }
            })
        val dec = Base64.getDecoder()
    }
}