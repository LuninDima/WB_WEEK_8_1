package com.example.wb_week_7.utils

private val translations = mapOf(
    "agi" to "ловкость",
    "int" to "Интелект",
    "str" to "Сила",
    "Melee" to "рукопашный",
    "Ranged" to "дальний",
)

fun translation(attributes: String): String {
    var translation = translations[attributes]
    return translation!!
}