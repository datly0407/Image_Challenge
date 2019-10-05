package com.datly.imagechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.datly.imagechallenge.data.ImageRepository
import com.datly.imagechallenge.data.model.Image
import com.datly.imagechallenge.data.model.Images

class ImageViewModel: ViewModel() {

    private var imageListObservable: LiveData<Images>? = null

    fun fetchImageListObservable(pageNumber: Int, searchTerm: String): LiveData<Images>? {
        imageListObservable = ImageRepository.getInstance().fetchImageList(pageNumber, searchTerm)

        return imageListObservable
    }
}