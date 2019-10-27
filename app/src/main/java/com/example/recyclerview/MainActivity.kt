package com.example.recyclerview

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    public val dataList = mutableListOf<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var storeID: String = "1"



/*
        b1.setOnClickListener {
            storeID = "1"
        }
        b1.setOnClickListener {
            storeID = "2"
        }
*/
        val receiver = Receiver()
        receiver.execute(storeID)

        //テストデータの生成
        val dataList = mutableListOf<Data>()

        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 2, true))/*
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 10, true))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 6, true))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 7, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
*/

        //RecyclerViewにAdapterとLayoutManagerを設定
        findViewById<RecyclerView>(R.id.testRecyclerView).also { recyclerView: RecyclerView ->
            recyclerView.adapter = Adapter(this, dataList)
            recyclerView.layoutManager = LinearLayoutManager(this)
        }


    }



}