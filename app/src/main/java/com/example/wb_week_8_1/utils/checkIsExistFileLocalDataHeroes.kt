package com.example.wb_week_8_1.utils
import com.example.wb_week_8_1.App

fun checkIsExistFileLocalDataHeroes(fileNameForData: String): Boolean {
    val file = App.getApplicationContext().getFileStreamPath(fileNameForData)
    return file.exists()
}