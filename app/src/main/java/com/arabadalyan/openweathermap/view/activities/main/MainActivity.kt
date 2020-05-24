package com.arabadalyan.openweathermap.view.activities.main

import android.os.Bundle
import com.arabadalyan.openweathermap.R
import com.arabadalyan.openweathermap.view.activities.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
