package com.harisewak.kamera.get_images_from_album_feature

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harisewak.kamera.data.Image
import com.harisewak.kamera.databinding.ItemImageBinding

class ImagesAdapter(private val images: List<Image>) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount() = images.size


}

class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(
    binding.root
) {

    fun bind(image: Image) {
        binding.root.setImageURI(Uri.parse(image.imageUri))
    }
}
