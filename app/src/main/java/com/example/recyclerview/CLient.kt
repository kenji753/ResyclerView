package com.example.recyclerview

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CLient {

    // APIのエンドポイントごとにメソッドを定義する
    @GET("/api/v2/items")
    //fun search() : Observable<MutableList<Data>>


    fun search(@Query("query") query: String): Observable<MutableList<Data>>
}