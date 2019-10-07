package com.datly.imagechallenge.data

import com.datly.imagechallenge.util.RestUtil

/**
 * This class acts as the bridge between ImgurApi and ImageRepository
 * @author: Dat Ly
 * Date: 10/04/2019
 */
object ImageRepositoryFactory {

    fun createImageRepository(): ImageRepository {
        val imgurApi = RestUtil.getInstance().retrofit.create(ImgurApi::class.java)
        return ImageRepository(imgurApi)
    }
}