package com.example.justin.resume_internal

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.background -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.skills -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.tastebudz -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.contact ->{
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
