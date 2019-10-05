package com.datly.imagechallenge.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.datly.imagechallenge.data.model.Image
import com.datly.imagechallenge.data.model.Images
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageRepository {

    companion object {

        @Synchronized
        fun getInstance(): ImageRepository {
            return ImageRepository()
        }
    }

    private lateinit var imgurApiCall: ImgurApi


//    fun init() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(ImgurApi.URL_LINK)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        imgurApiCall = retrofit.create(ImgurApi::class.java)
//    }

    fun fetchImageList(pageNumber: Int, searchTerm: String): LiveData<Images> {
        val clientBuilder = OkHttpClient.Builder()

        val headerAuthInterceptor = Interceptor { chain ->
            var request = chain.request()
            val headers = request.headers().newBuilder().add("authorization", "Client-ID 126701cd8332f32").build()
            request = request.newBuilder().headers(headers).build()
            chain.proceed(request)
        }

        clientBuilder.addInterceptor(headerAuthInterceptor)
        val client = clientBuilder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ImgurApi.URL_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        imgurApiCall = retrofit.create(ImgurApi::class.java)

        val imageList = MutableLiveData<Images>()
        imgurApiCall.fetchImageList(pageNumber = pageNumber, searchTerm = searchTerm)
            .enqueue(object: Callback<Images> {
                override fun onResponse(call: Call<Images>, response: Response<Images>) {
                    imageList.value = response.body()
                    Log.d("DLTEST", response.raw().toString())
                }

                override fun onFailure(call: Call<Images>, t: Throwable) {
                }
            })

        return imageList
    }
}