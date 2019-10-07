package com.datly.imagechallenge.util

import com.datly.imagechallenge.data.ImgurApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Establish connection between the application with endpoint API
 * using Retrofit2
 * @author: Dat Ly
 * Date: 10/04/2019
 */
class RestUtil private constructor() {
    val retrofit: Retrofit

    companion object {

        @Synchronized
        fun getInstance(): RestUtil {
            return RestUtil()
        }
    }

    init {
        val clientBuilder = OkHttpClient.Builder()
        val headerAuthInterceptor = Interceptor {chain ->
            var request = chain.request()
            val headers = request.headers().newBuilder().add(
                "authorization",
                "Client-ID 126701cd8332f32").build()
            request = request.newBuilder().headers(headers).build()
            chain.proceed(request)
        }

        clientBuilder.addInterceptor(headerAuthInterceptor)
        val client = clientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(ImgurApi.URL_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}