package com.example.wb_week_8_1.repository

import android.content.Context
import com.example.wb_week_8_1.App

class LocalDataSource {
    fun getDataHeroes(fileNameForData: String): String {
            val data = App.getApplicationContext().openFileInput(fileNameForData).bufferedReader()
                .use {
                    it.readText()
                }
            return data
    }

    fun saveDataHeroes(fileNameForData: String, dataHeroes: String) {
            App.getApplicationContext().openFileOutput(fileNameForData, Context.MODE_PRIVATE).use {
                it?.write(dataHeroes.toByteArray())
            }
    }
}