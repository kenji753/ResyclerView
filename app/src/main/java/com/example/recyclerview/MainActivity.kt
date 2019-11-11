package com.example.recyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    private val retrofit: Retrofit by lazy { createRetrofit() }
    private val sampleClient: SampleClient by lazy { retrofit.create(SampleClient::class.java) }

    private lateinit var moodadapter: ListAdapter
    private lateinit var foodadapter: ListAdapter
    private lateinit var drinkadapter: ListAdapter
    private lateinit var dessertadapter: ListAdapter

    private lateinit var recyclerView: RecyclerView

    private val disposable = CompositeDisposable()

    //private var dataList: MutableList<Data> = mutableListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        setUpFoodView()
        setUpDessertView()
        setUpDrinkView()
        requestItem()


    }

    private fun setUpView() {
        recyclerView = findViewById(R.id.mood)
        moodadapter = ListAdapter(this)
        recyclerView.apply {
            val manager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            layoutManager = manager
            adapter = this@MainActivity.moodadapter
        }
    }

    private fun setUpFoodView(){
        recyclerView = findViewById(R.id.food)
        foodadapter = ListAdapter(this)
        recyclerView.apply {
            val manager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            layoutManager = manager
            adapter = this@MainActivity.foodadapter
        }
        sampleClient.getSample()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                foodadapter.run {
                    // アダプターにデータ追加
                    stores.addAll(data.storeID)
                    // データ追加後はnotifyDataSetChangedとかを呼ばないと変更されない
                    notifyDataSetChanged()
                }
            }) {
                Log.e(MainActivity::class.java.simpleName, it.toString())
            }.addTo(disposable)
    }
    private fun setUpDessertView(){
        recyclerView = findViewById(R.id.dessert)
        dessertadapter = ListAdapter(this)
        recyclerView.apply {
            val manager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            layoutManager = manager
            adapter = this@MainActivity.dessertadapter
        }
        sampleClient.getSample()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                dessertadapter.run {
                    // アダプターにデータ追加
                    stores.addAll(data.storeID)
                    // データ追加後はnotifyDataSetChangedとかを呼ばないと変更されない
                    notifyDataSetChanged()
                }
            }) {
                Log.e(MainActivity::class.java.simpleName, it.toString())
            }.addTo(disposable)
    }
    private fun setUpDrinkView(){
        recyclerView = findViewById(R.id.drink)
        drinkadapter = ListAdapter(this)
        recyclerView.apply {
            val manager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            layoutManager = manager
            adapter = this@MainActivity.drinkadapter
        }
        sampleClient.getSample()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                drinkadapter.run {
                    // アダプターにデータ追加
                    stores.addAll(data.storeID)
                    // データ追加後はnotifyDataSetChangedとかを呼ばないと変更されない
                    notifyDataSetChanged()
                }
            }) {
                Log.e(MainActivity::class.java.simpleName, it.toString())
            }.addTo(disposable)
    }
    private fun requestItem() {
        sampleClient.getSample()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                moodadapter.run {
                    // アダプターにデータ追加
                    stores.addAll(data.storeID)
                    // データ追加後はnotifyDataSetChangedとかを呼ばないと変更されない
                    notifyDataSetChanged()
                }
            }) {
                Log.e(MainActivity::class.java.simpleName, it.toString())
            }.addTo(disposable)
    }


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
        private const val BASE_URL = "https://jsondata.okiba.me"
    }


}