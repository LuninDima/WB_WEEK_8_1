package com.example.wb_week_7.repository

import okhttp3.Callback

interface Repository {
    fun getListHeroesFromServer(requestLink: String, callback: Callback)

    fun getDataHeroesFromLocalData(fileNameForData: String): String

    fun saveDataHeroesToLocalData(fileNameForData: String, dataHeroes: String)
}