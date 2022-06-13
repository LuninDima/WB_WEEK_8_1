package com.example.wb_week_7.utils
import com.example.wb_week_7.App

fun checkIsExistFileLocalDataHeroes(fileNameForData: String): Boolean {
    val file = App.getApplicationContext().getFileStreamPath(fileNameForData)
    return file.exists()
}