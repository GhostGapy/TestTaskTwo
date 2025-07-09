package com.example.testtasktwo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.testtasktwo.R
import com.github.chrisbanes.photoview.PhotoView


class ShowImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_photo)

        val photoUrl = intent.getStringExtra("photo_url")

        val photoView = findViewById<PhotoView>(R.id.fullscreenPhoto)
        Glide.with(this)
            .load(photoUrl)
            .into(photoView)
    }
}
