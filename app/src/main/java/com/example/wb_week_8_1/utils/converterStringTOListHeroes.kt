package com.example.wb_week_8_1.utils

import com.example.wb_week_8_1.model.Hero
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

fun convertStringTOListHeroes(serverResponse: String): List<Hero>? {
    val listHeroes: List<Hero>?
    val moshi: Moshi = Moshi.Builder().build()
    val listType = Types.newParameterizedType(List::class.java, Hero::class.java)
    val jsonAdapter: JsonAdapter<List<Hero>> = moshi.adapter(listType)
    listHeroes = jsonAdapter.fromJson(serverResponse)
    return listHeroes
}