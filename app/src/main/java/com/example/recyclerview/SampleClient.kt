package com.example.recyclerview

import io.reactivex.Observable
import retrofit2.http.GET


interface SampleClient {

    // APIのエンドポイントごとにメソッドを定義する
    @GET("/v1/json/jfARj191105130858")
    fun getSample(): Observable<Data>
}