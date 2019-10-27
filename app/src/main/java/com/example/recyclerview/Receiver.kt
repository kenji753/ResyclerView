package com.example.recyclerview

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

//doInBackgroundメソッドの引数の型, onProgressUpdateメソッドの引数の型, onPostExecuteメソッドの戻り値の型
class Receiver : AsyncTask<String, String, String>() {
    override fun doInBackground(vararg params: String?): String {
        val id =params[0]
        //URLの尻に都市IDくっつける
        val urlStr = "https://jsondata.okiba.me/v1/json/s0oFo191027144849"

        //URLオブジェクト生成
        val url = URL(urlStr)
        val con = url.openConnection() as HttpURLConnection
        //http接続メソッド設定
        con.requestMethod = "GET"
        //接続
        con.connect()
        //接続エラーの処理書く

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
        //[]、配列をよみとる
        val StoreIDJSON = rootJSON.getJSONArray("storeID")
        val Sdata = StoreIDJSON.getJSONObject(0)
        val storeName = Sdata.getString("name")
        val storePicture = Sdata.getString("picture")
        val PostData = Sdata.getJSONObject("post")
        val contentData = PostData.getString("content")
        val likeCount = PostData.getString("likeCount").toInt()
        val likeState = PostData.getString("likeState").toBoolean()


        Data(storeName,contentData,storePicture,likeCount,likeState)
        /*


        val desc  = descriptionJSON.getString("text")
        //更新時間
        val time  = descriptionJSON.getString("publicTime")
        Log.d("時間", time)

        //ルートJSON直下の「forecasts」JSON配列を取得
        val forecasts = rootJSON.getJSONArray("forecasts")
        val forecastNow = forecasts.getJSONObject(0)
        //天気取得
        val telop = forecastNow.getString("telop")

        val pushtime = "更新時間：" + time
        */

        //天気情報文字列をTextviewにいれる
       //kokoni teki touna hairetu
        //oite watrasu





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