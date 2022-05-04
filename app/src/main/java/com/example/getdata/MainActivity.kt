package com.example.getdata

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.getdata.adapter.ItemAdapter
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient()
        val url = "https://api.github.com/legacy/repos/search/language:kotlin?sort=stars"
        val req = Request.Builder()
            .url(url)
            .build()
        val cont  = this

        client.newCall(req)
            .enqueue(object : Callback{
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    // println(body)
                    val gson = GsonBuilder().create()
                    val feed = gson.fromJson(body, ApiData::class.java)
                    runOnUiThread{
                        drawOnScreen(feed.repositories)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    println("Failed")
                }
            })
    }
    fun drawOnScreen(l : List<Repository>){
        val recycleView : RecyclerView = findViewById(R.id.RecyclerView)
        recycleView.adapter = ItemAdapter(this,l )
    }
}

