package com.example.wb_week_5.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wb_week_5.R
import com.example.wb_week_5.view.listHeroes.ListHeroesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListHeroesFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
}