package com.example.testtasktwo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AlbumApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: AlbumService = retrofit.create(AlbumService::class.java)
}
