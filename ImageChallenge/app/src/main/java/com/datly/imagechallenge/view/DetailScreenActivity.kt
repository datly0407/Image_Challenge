package com.datly.imagechallenge.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.datly.imagechallenge.R
import kotlinx.android.synthetic.main.activity_detail_screen.*

/**
 * FullScreenActivity that will display full screen image with its title
 * @author: Dat Ly
 * Date: 10/04/2019
 */
class DetailScreenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail_screen)
        supportActionBar?.hide()

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = Color.TRANSPARENT
        }
        setUpView()

    }

    private fun setUpView() {
        val bundle = intent.extras

        Glide.with(this)
            .load(bundle?.getString(EXTRA_IMAGE_URL))
            .into(image_view)

        title_view.text = bundle?.getString(EXTRA_IMAGE_TITLE)
        title_view.setTextColor(Color.WHITE)

        frame_container.bringToFront()
        frame_container.setOnClickListener { onBackPressed() }
    }
}