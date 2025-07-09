package com.example.testtasktwo

data class Album (
    val userId: Int,
    val id: Int,
    val title: String
)

data class Photo (
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

