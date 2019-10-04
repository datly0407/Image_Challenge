package com.datly.imagechallenge.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.datly.imagechallenge.R

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val mainScreenFragment = MainScreenFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, mainScreenFragment, MainScreenFragment.TAG)
            transaction.commit()
        }

        setContentView(R.layout.activity_main)
    }

    /**
     * MainScreenFragment will call this method to move to DetailScreenFragment
     */
    fun navigate(data: String) {

    }
}