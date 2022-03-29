package com.jhsong.aipowergrid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replace(HomeFragment())
        // menu  https://www.jianshu.com/p/1250f3ba58c8
    }

    private fun replace(fragment: Fragment) {
        if (!fragment.isAdded) {
            supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment)
                .commitAllowingStateLoss()
        }
    }
}