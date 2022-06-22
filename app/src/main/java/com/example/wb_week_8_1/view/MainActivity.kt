package com.example.wb_week_8_1.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.wb_week_8_1.R
import com.example.wb_week_8_1.view.about.AboutFragment
import com.example.wb_week_8_1.view.listHeroes.ListHeroesFragment

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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuMainFragment -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .replace(R.id.fragment_container, ListHeroesFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            R.id.menuAboutFragment -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .replace(R.id.fragment_container, AboutFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }
}