package com.datly.imagechallenge.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.datly.imagechallenge.R
import com.datly.imagechallenge.data.ImageRepositoryFactory
import com.datly.imagechallenge.data.model.ImageList
import com.datly.imagechallenge.view.adapter.MainScreenAdapter
import com.datly.imagechallenge.viewmodel.MainActivityViewModel
import com.datly.imagechallenge.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

/**
 * First screen of the application, it will display a list of images/titles
 * related to the searchTerm that user enter.
 * @author: Dat Ly
 * Date: 10/04/2019
 */
class MainActivity: AppCompatActivity() {

    private var currentPage = 1
    private var searchTerm = ""

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ImageRepositoryFactory.createImageRepository())).get(MainActivityViewModel::class.java)
        setUpView()
    }

    override fun onResume() {
        super.onResume()
        bind()
    }

    override fun onPause() {
        super.onPause()
        unbind()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu_bar, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.search_query_hint)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                currentPage = 1
                /*
                 * Need to remove RV when searching new term
                 */
                mainActivityViewModel.getImageList(-1, " ")
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                /*
                 * Pass the query that user entered to ViewModel
                 * in order to fetch data from endpoint
                 */
                if (!query.isNullOrEmpty()) {
                    searchTerm = query
                    mainActivityViewModel.getImageList(currentPage, searchTerm)
                }

                searchItem.collapseActionView()
                main_screen_spinner.visibility = View.VISIBLE
                return false
            }
        })

        return true
    }

    /**
     * Set up all necessary UI components
     */
    private fun setUpView() {
        val layoutManager = LinearLayoutManager(this)
        rv_item.layoutManager = layoutManager
        rv_item.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                    //We have reached the end of the RecyclerView
                    //move to next page of the gallery
                    main_screen_spinner.visibility = View.VISIBLE
                    currentPage++
                    mainActivityViewModel.getImageList(currentPage, searchTerm)
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    /**
     * Set the owner to MainActivity and
     * start observing on ViewModel
     */
    private fun bind() {
        //observe ImageList
        mainActivityViewModel.imageListLiveData.observe(this, Observer {
            fetchImageList(it)
        })
    }

    /**
     * Remove all the observer when our activity is going to be destroy
     */
    private fun unbind() {
        mainActivityViewModel.imageListLiveData.removeObservers(this)
    }

    /**
     * Receive the list of image from ViewModel and update
     * the UI accordingly based on the returned data
     * @param otherImageList: list of image
     */
    private fun fetchImageList(otherImageList: ImageList) {
        //display result data from ViewModel
        val adapter = MainScreenAdapter(
            ::onImageClickCallback,
            otherImageList,
            main_screen_spinner)
        rv_item.adapter = adapter
    }

    /**
     * Callback method when user click on any item of RecyclerView
     * @param imageUrl: link to image
     * @param imageTitle: title of chosen image
     */
    private fun onImageClickCallback(imageUrl: String, imageTitle: String) {
        val intent = Intent(this, DetailScreenActivity::class.java).apply {
            putExtra(EXTRA_IMAGE_URL, imageUrl)
            putExtra(EXTRA_IMAGE_TITLE, imageTitle)
        }
        startActivity(intent)
        //set custom enter animation
        overridePendingTransition(R.anim.fade_in, R.anim.no_animation)
    }
}