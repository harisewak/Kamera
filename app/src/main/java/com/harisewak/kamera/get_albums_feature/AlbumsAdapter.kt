package com.harisewak.kamera.get_albums_feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.harisewak.kamera.data.Album
import com.harisewak.kamera.databinding.ItemAlbumBinding

class AlbumsAdapter : ListAdapter<Album, AlbumViewHolder>(DIFF_CALLBACK) {

    private lateinit var click: (album: Album) -> Unit

    companion object {

        val DIFF_CALLBACK =

            object : DiffUtil.ItemCallback<Album>() {

                override
                fun areItemsTheSame(
                    oldAlbum: Album, newAlbum: Album
                ): Boolean {
                    return oldAlbum.id == newAlbum.id
                }

                override
                fun areContentsTheSame(
                    oldAlbum: Album, newAlbum: Album
                ): Boolean {
                    return oldAlbum.equals(newAlbum)
                }
            }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(currentList[position], click)
    }

    fun setItemClickListener(function: (album: Album) -> Unit) {
        click = function
    }
}