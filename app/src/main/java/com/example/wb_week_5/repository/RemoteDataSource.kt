package com.example.wb_week_5.repository

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class RemoteDataSource {
    fun getListHero(request: String, callback: Callback) {
        val builder: Request.Builder = Request.Builder().apply {
            url(request)
        }
        OkHttpClient().newCall(builder.build()).enqueue(callback)


    }


}