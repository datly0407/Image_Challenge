package com.datly.imagechallenge.data

import com.datly.imagechallenge.data.model.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApi {

    companion object {
        private const val URL_LINK = "https://api.imgur.com/"
    }

    @GET("3/gallery/search/time/{page_number}")
    fun getImageList(
        @Path("page_number") pageNumber: Int,
        @Query("q") searchTerm: String): Call<List<Image>>


}