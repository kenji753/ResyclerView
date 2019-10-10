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
        val date = "1hour"
        val memoList = mutableListOf<Memo>()
        repeat((0..100).count()) { memoList.add(Memo("僕の名前は麻婆", "1 hours ago")) }

        //RecyclerViewにAdapterとLayoutManagerを設定
        findViewById<RecyclerView>(R.id.testRecyclerView).also { recyclerView: RecyclerView ->
            recyclerView.adapter = Adapter(this, memoList)
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
}