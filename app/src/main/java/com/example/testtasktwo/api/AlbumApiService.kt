package com.example.testtasktwo.api
import com.example.testtasktwo.Album
import com.example.testtasktwo.Photo
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}/photos")
    suspend fun getPhotos(@Path("id") albumId: Int): List<Photo>
}
