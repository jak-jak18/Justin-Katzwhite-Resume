package com.example.justin.resume_internal

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal var LOG_TAG = "MainActivity"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.background -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_container, BackgroundFrag.newInstance())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.skills -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_container, SkillsFrag.newInstance())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.tastebudz -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_container, TastebudzFrag.newInstance())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.contact ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_container, ContactFrag.newInstance())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container, BackgroundFrag.newInstance())
            .commit()
    }
}
