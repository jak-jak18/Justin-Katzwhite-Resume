package com.example.justin.resume_internal

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val LOG_TAG = "MainActivity"

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
            R.id.contact -> {
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
        setTheme(R.style.AppTheme)
        try {
            TimeUnit.SECONDS.sleep(1)
        }catch (e: InterruptedException){

        }
        getPreferences(Context.MODE_PRIVATE)
        setContentView(R.layout.activity_main)
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container, BackgroundFrag.newInstance())
            .commit()
    }


}
