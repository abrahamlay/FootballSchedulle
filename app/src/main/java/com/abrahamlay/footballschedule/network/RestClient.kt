package com.abrahamlay.footballschedule.network

import com.abrahamlay.footballschedule.BuildConfig
import retrofit.Retrofit
import retrofit.GsonConverterFactory
import retrofit.RxJavaCallAdapterFactory
import com.google.gson.GsonBuilder

/**
 * Created by Abraham on 20/04/2018.
 */
class RestClient {
    companion object {
        var gson=GsonBuilder().create()
        var retrofit:Retrofit=Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.BASE_URL)
                .build()
    }
}