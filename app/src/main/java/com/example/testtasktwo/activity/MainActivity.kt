package com.example.testtasktwo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testtasktwo.viewmodel.AlbumViewModel
import com.example.testtasktwo.R
import com.example.testtasktwo.adapter.AlbumAdapter
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumViewModel
    private lateinit var adapter: AlbumAdapter
    private lateinit var loadingProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        loadingProgressBar = findViewById(R.id.loadingProgressBar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // dobimo viewmodel za delo s podatki o albumu
        viewModel = ViewModelProvider(this)[AlbumViewModel::class.java]

        //nastavimo recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.albumRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AlbumAdapter(emptyList()) { album ->
            loadingProgressBar.visibility = View.VISIBLE
            viewModel.onAlbumSelected(album)
        }
        recyclerView.adapter = adapter

        // sprejemamo podatke iz viewmodela asinhrono tudi ce ne uporabljamo LiveData
        viewModel.albums.observe(this) { albumList ->
            adapter.updateAlbums(albumList)
        }

        // navigacija do PhotoActivity ob kliku na album z posiljanjem
        viewModel.navigateToPhotos.observe(this) { selectedAlbum ->
            loadingProgressBar.visibility = View.GONE
            val intent = Intent(this, PhotoActivity::class.java)
            intent.putExtra("album_id", selectedAlbum.id)
            intent.putExtra("album_title", selectedAlbum.title)
            startActivity(intent)
        }
    }
}
