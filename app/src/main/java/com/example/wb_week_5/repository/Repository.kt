package com.example.wb_week_5.repository

import okhttp3.Callback

interface Repository {
    fun getHeroFromServer(requestLink: String, callback: Callback)
}