package com.harisewak.kamera.get_albums_feature

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.harisewak.kamera.data.Album
import com.harisewak.kamera.databinding.ItemAlbumBinding

class AlbumViewHolder(
    private val binding: ItemAlbumBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(album: Album, click: (album: Album) -> Unit) {

        binding.root.setOnClickListener { click(album) }

        binding.ivFirstImage.load(Uri.parse(album.firstImageUri)) {
            crossfade(true)
        }
    }
}