package com.example.cleanarchitecturepractice.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cleanarchitecturepractice.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (savedInstanceState == null) {
//            val fmManager = supportFragmentManager.beginTransaction()
//                .replace(R.id.frameContent, ListFragment.newInstance("", ""))
//            fmManager.commit()
//        }

    }
}
