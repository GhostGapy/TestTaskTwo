package com.example.testtasktwo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasktwo.Album
import com.example.testtasktwo.api.AlbumApiClient
import kotlinx.coroutines.launch

class AlbumViewModel : ViewModel() {

    val albums = MutableLiveData<List<Album>>()
    val navigateToPhotos = MutableLiveData<Album>()

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val result = AlbumApiClient.service.getAlbums()
                albums.value = result
                Log.d("TEST", "Albumi uspešno naloženi: ${result.size} albumov")
            } catch (e: Exception) {
                Log.e("TEST", "Napaka: ${e.message}")
            }
        }
    }

    fun onAlbumSelected(album: Album) {
        navigateToPhotos.value = album
    }
}
