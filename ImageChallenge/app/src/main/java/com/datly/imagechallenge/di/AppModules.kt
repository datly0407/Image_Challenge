package com.datly.imagechallenge.di

import com.datly.imagechallenge.data.ImageRepository
import com.datly.imagechallenge.data.ImgurApi
import com.datly.imagechallenge.util.RestUtil
import com.datly.imagechallenge.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory { RestUtil.getInstance().retrofit.create(ImgurApi::class.java) }
    single { ImageRepository(get()) }
    viewModel { MainActivityViewModel(get()) }
}