package com.datly.imagechallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.datly.imagechallenge.data.ImageRepository

/**
 * This class will instantiate ViewModel object with custom constructor
 * @author: Dat Ly
 * Date: 10/04/2019
 */
class ViewModelFactory(
    private val imageRepository: ImageRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(imageRepository) as T
    }
}