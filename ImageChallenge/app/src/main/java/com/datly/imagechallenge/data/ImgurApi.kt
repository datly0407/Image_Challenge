package com.datly.imagechallenge.data

import com.datly.imagechallenge.data.model.ImageList
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Service class that will interact with endpoint API
 * @author: Dat Ly
 * Date: 10/04/2019
 */
interface ImgurApi {

    companion object {
        const val URL_LINK = "https://api.imgur.com/3/gallery/search/time/"
    }

    @GET("{page_number}")
    fun fetchImageList(
        @Path("page_number") pageNumber: Int,
        @Query("q") searchTerm: String): Observable<Response<ImageList>>
}