package com.datly.imagechallenge.data

import com.datly.imagechallenge.data.model.Image
import com.datly.imagechallenge.data.model.Images
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApi {

    companion object {
        const val URL_LINK = "https://api.imgur.com/3/gallery/search/time/"
    }

    @GET("{page_number}")
    fun fetchImageList(
        @Path("page_number") pageNumber: Int,
        @Query("q") searchTerm: String): Call<Images>


}