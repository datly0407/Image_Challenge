package com.datly.imagechallenge.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.datly.imagechallenge.R

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu_bar, menu)

        return super.onCreateOptionsMenu(menu)
    }
}