package com.byansanur.awesomeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.byansanur.awesomeapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun hasWindowFocus(): Boolean {
        return super.hasWindowFocus()

    }
}