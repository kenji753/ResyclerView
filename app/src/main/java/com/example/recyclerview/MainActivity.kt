package com.example.recyclerview

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.sql.ClientInfoStatus
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    private val retrofit: Retrofit by lazy {createRetrofit()}
    private val client: CLient by lazy { retrofit.create(CLient::class.java) }

    private lateinit var adapter: ListAdapter
    private lateinit var recyclerView: RecyclerView

    private val disposable = CompositeDisposable()

    //private var dataList: MutableList<Data> = mutableListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        requestItem()
        adapter.articles.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 2, true))


    }

    private fun setUpView() {
        recyclerView = findViewById(R.id.testRecyclerView)
        adapter = ListAdapter(this)
        recyclerView.adapter = adapter
    }

    private fun requestItem() {
        client.search("Kotlin")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                adapter.run {
                    articles = data
                    notifyDataSetChanged()
                }
            }) {
                Log.e(MainActivity::class.java.simpleName, it.toString())
            }.addTo(disposable)
    }

    /*

       // ARepository.

        var storeID = "1"

        val receiver = Receiver()


        b1.setOnClickListener {
            storeID = "1"
            receiver.execute(storeID)
            //RecyclerViewにAdapterとLayoutManagerを設定
            findViewById<RecyclerView>(R.id.testRecyclerView).also { recyclerView: RecyclerView ->
                recyclerView.adapter = Adapter(this, dataList)
                recyclerView.layoutManager = LinearLayoutManager(this)
            }
        }

            b2.setOnClickListener {
             storeID = "2"
             dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 2, true))

            //receiver.execute(storeID)

            //RecyclerViewにAdapterとLayoutManagerを設定
            findViewById<RecyclerView>(R.id.testRecyclerView).also { recyclerView: RecyclerView ->
                recyclerView.adapter = Adapter(this, dataList)
                recyclerView.layoutManager = LinearLayoutManager(this)
            }
        }




        //テストデータの生成
        //val dataList = mutableListOf<Data>()

        /*dataList.add(Data("ユーザー名","テキストてきすとテキストてきすとテキストてきすとテキストてきすと" ,"1 hours ago", 2, true))
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


    inner class Receiver : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {
            android.os.Debug.waitForDebugger()
            val urlStr = "https://jsondata.okiba.me/v1/json/s0oFo191027144849"

            //URLオブジェクト生成
            val url = URL(urlStr)
            val con = url.openConnection() as HttpURLConnection
            //http接続メソッド設定
            con.requestMethod = "GET"
            //接続
            con.connect()
            //接続エラーの理書く

            //HttpURLConnectionオブジェクトからレスポンスデータ取得。天気情報が格納されとる
            val stream  = con.inputStream
            //JSON文字列に変換
            val result = is2String(stream)
            //解放
            con.disconnect()
            stream.close()

            //JSON文字列をリターンする
            return result
        }

        override fun onPostExecute(result: String?) {

            //JSON文字列からJSONObjectを生成。
            val rootJSON = JSONObject(result)
            //getJSONArrayで [],配列をよみとる
            val StoreIDJSON = rootJSON.getJSONArray("storeID")
            val Sdata = StoreIDJSON.getJSONObject(0)
            val storeData = Sdata.getJSONObject("store")
            val storeName = storeData.getString("name")
            val storePicture = storeData.getString("picture")
            val PostData = Sdata.getJSONObject("post")
            val contentData = PostData.getString("content")
            val likeCount = PostData.getString("likeCount").toInt()
            val likeState = PostData.getString("likeState").toBoolean()


            dataList.add(Data(storeName,contentData,storePicture,likeCount,likeState))

            findViewById<RecyclerView>(R.id.testRecyclerView).also { recyclerView: RecyclerView ->
                recyclerView.adapter = Adapter(this@MainActivity, dataList)
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }


        }

        //文字列に変換するprivateメソッド(Inputstreamをstringに変換する定型処理らしい)
        private fun is2String(stream: InputStream): String{
            val sb = StringBuilder()
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var line = reader.readLine()
            while(line != null){
                sb.append(line)
                line = reader.readLine()
            }
            reader.close()
            return sb.toString()
        }


    }

     */

    // retrofit(APIを叩くライブラリ)のインスタンス生成
    private fun createRetrofit(): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    // メモリリーク対策
    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    companion object {
        private const val BASE_URL = "https://qiita.com"
    }


}