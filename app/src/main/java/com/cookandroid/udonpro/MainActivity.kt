package com.cookandroid.udonpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.btn_home -> {
                        var page1 = MainForm()
                        supportFragmentManager.beginTransaction().replace(R.id.main_content, page1)
                            .commit()
                    }
                    R.id.btn_favorites -> {
                        var page2 = FavoriteList()
                        supportFragmentManager.beginTransaction().replace(R.id.main_content, page2)
                            .commit()
                    }
                    R.id.btn_registerBook -> {
                        var page3 = RegisterBook()
                        supportFragmentManager.beginTransaction().replace(R.id.main_content, page3)
                            .commit()
                    }
                    R.id.btn_mypage -> {
                        var page4 = newMypage()
                        supportFragmentManager.beginTransaction().replace(R.id.main_content, page4)
                            .commit()
                    }
                }
                true
            }
            selectedItemId = R.id.btn_home
        }

    }
}

