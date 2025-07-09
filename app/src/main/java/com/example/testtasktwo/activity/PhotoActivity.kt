package com.example.testtasktwo.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasktwo.R
import com.example.testtasktwo.PhotoAdapter
import com.example.testtasktwo.viewmodel.PhotoViewModel
import androidx.appcompat.widget.Toolbar


class PhotoActivity : AppCompatActivity() {

    private lateinit var viewModel: PhotoViewModel
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val toolbar = findViewById<Toolbar>(R.id.photoToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val albumTitle = intent.getStringExtra("album_title")
        supportActionBar?.title = albumTitle ?: "Photos"

        // Preverimo ce je bil poslan album_id
        val albumId = intent.getIntExtra("album_id", -1)
        if (albumId == -1) {
            finish()
            return
        }

        // ViewModel
        viewModel = ViewModelProvider(this)[PhotoViewModel::class.java]

        // RecyclerView init
        val recyclerView = findViewById<RecyclerView>(R.id.photoRecyclerView)
        adapter = PhotoAdapter(emptyList()) { photo ->
            // ob kliku odpremo fullscreen aktivnost za ogled slike
            val intent = Intent(this, ShowImageActivity::class.java)
            val encodedTitle = Uri.encode(photo.title)
            val imageUrl = "https://placehold.co/600x600.png?text=$encodedTitle"
            intent.putExtra("photo_url", imageUrl)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.photos.observe(this) { photos ->
            adapter.updatePhotos(photos)
        }

        // zazenemo nalaganje slik
        viewModel.loadPhotos(albumId)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
