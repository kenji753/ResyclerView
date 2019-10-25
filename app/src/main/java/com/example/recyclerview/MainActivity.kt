package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //テストデータの生成
        val dataList = mutableListOf<Data>()
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 2, true))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 10, true))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 6, true))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 7, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))
        dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 0, false))


        //RecyclerViewにAdapterとLayoutManagerを設定
        findViewById<RecyclerView>(R.id.testRecyclerView).also { recyclerView: RecyclerView ->
            recyclerView.adapter = Adapter(this, dataList)
            recyclerView.layoutManager = LinearLayoutManager(this)
        }


    }
}