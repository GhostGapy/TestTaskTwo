package com.example.testtasktwo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtasktwo.Photo
import com.example.testtasktwo.api.AlbumApiClient
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {

    val photos = MutableLiveData<List<Photo>>()

    fun loadPhotos(albumId: Int) {
        viewModelScope.launch {
            try {
                val result = AlbumApiClient.service.getPhotos(albumId)
                photos.value = result
                Log.d("TEST", "Naloženih slik: ${result.size}")
            } catch (e: Exception) {
                Log.e("TEST", "Napaka pri nalaganju slik: ${e.message}")
            }
        }
    }
}
