package com.example.wb_week_5.viewModel

import com.example.wb_week_5.model.Hero

sealed class AppStateList {
    data class Success(val listHero: List<Hero>) : AppStateList()
    data class Error(val error: Throwable) : AppStateList()
    object Loading : AppStateList()
}