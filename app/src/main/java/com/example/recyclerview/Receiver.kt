/*
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
        android.os.Debug.waitForDebugger();
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
        //接続エラーの理書く

        val res = con.responseCode
        Log.d("result", res.toString())

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

        val dataList = mutableListOf<Data>()
        dataList.add(Data(storeName,contentData,storePicture,likeCount,likeState))

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