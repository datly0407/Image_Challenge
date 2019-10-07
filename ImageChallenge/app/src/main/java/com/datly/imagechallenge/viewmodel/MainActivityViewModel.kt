package com.datly.imagechallenge.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.datly.imagechallenge.data.ImageRepository
import com.datly.imagechallenge.data.model.ImageList

/**
 * ViewModel class will receive the user's event from View and pass it to the Model.
 * This class will fire event back to View if there is any change with the data.
 * @author: Dat Ly
 * Date: 10/04/2019
 */
class MainActivityViewModel(val imageRepository: ImageRepository): ViewModel() {

    private val _imageListLiveData: MutableLiveData<ImageList> = MutableLiveData()
    val imageListLiveData: LiveData<ImageList> = _imageListLiveData

    @SuppressLint("CheckResult")
    fun getImageList(pageNumber: Int, searchTerm: String) {
        imageRepository
            .fetchImageList(pageNumber, searchTerm)
            .subscribe( {
                _imageListLiveData.postValue(it)
            }, {throwable ->
                throwable.printStackTrace()
            })
    }
}