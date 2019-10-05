package com.datly.imagechallenge.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.datly.imagechallenge.R
import com.datly.imagechallenge.data.ImageRepository
import com.datly.imagechallenge.data.model.Image
import com.datly.imagechallenge.data.model.Images
import com.datly.imagechallenge.view.adapter.MainScreenAdapter
import com.datly.imagechallenge.viewmodel.ImageViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private lateinit var imageList: List<Image>
    private lateinit var imageViewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ImageRepository.getInstance().init()

        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)

        setUpView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu_bar, menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpView() {
        imageViewModel.fetchImageListObservable(1, "cats")?.observe(
            this,
            Observer<Images>{ data ->
                val mainScreenAdapter = MainScreenAdapter(::onImageClickCallBack, data)
                val layoutManager = LinearLayoutManager(this)
                rv_item.layoutManager = layoutManager
                rv_item.adapter = mainScreenAdapter
            })

//        startObserveData(mainScreenAdapter)
    }

    private fun onImageClickCallBack(position: Int) {
        Toast.makeText(
            this,
            "$position",
            Toast.LENGTH_SHORT
        ).show()
    }

//    private fun startObserveData(adapter: MainScreenAdapter) {
//        imageViewModel.fetchImageListObservable(1, "cats")?.observe(
//            this,
//            Observer<Images>{ data ->
//
//            })
//    }
}