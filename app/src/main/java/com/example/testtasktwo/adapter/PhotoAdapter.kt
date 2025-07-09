package com.example.testtasktwo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.util.Log

class PhotoAdapter(
    private var photos: List<Photo>,
    private val onPhotoClick: (Photo) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView = view.findViewById(R.id.photoThumbnail) // slika
        val title: TextView = view.findViewById(R.id.photoTitle) // naslov slike
    }

    // ustvarimo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]

        // Prikaži naslov
        holder.title.text = photo.title

        // Pretvori naslov v tekst za URL
        val textForUrl = photo.title.replace(" ", "%20")
        Log.d("PhotoTest", "Text for URL: $textForUrl")

        val imageUrl = "https://placehold.co/200x200.png?text=$textForUrl"
        Log.d("PhotoTest", "Image URL: $imageUrl")

        // nalozimo sliko
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.thumbnail)

        // kliknemo sliko
        holder.itemView.setOnClickListener {
            onPhotoClick(photo)
        }
    }

    override fun getItemCount(): Int = photos.size

    fun updatePhotos(newPhotos: List<Photo>) {
        photos = newPhotos
        notifyDataSetChanged()
    }

}
