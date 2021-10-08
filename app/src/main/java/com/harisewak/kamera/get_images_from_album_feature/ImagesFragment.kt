package com.harisewak.kamera.get_images_from_album_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.harisewak.kamera.R
import com.harisewak.kamera.data.Image
import com.harisewak.kamera.databinding.FragmentImagesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment() {
    private lateinit var binding: FragmentImagesBinding
    private val viewModel: GetImagesViewModel by viewModels()
    private val TAG = "GetImagesFragment"
    private val args by navArgs<ImagesFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val albumId = args.albumId

        if (albumId == -1L) {
            // show error and stop further execution
            binding.tvMessage.text = getString(R.string.msg_album_id_invalid)
            return
        }

        lifecycleScope.launchWhenStarted {

            when (val response = viewModel.getImages(albumId)) {

                is GetImagesResponse.Success -> {
                    if (response.images.isEmpty()) {
                        // no images
                        binding.tvMessage.text = getString(R.string.msg_no_images_in_album)
                    } else {
                        // show images
                        loadImages(response.images)
                    }
                }

                is GetImagesResponse.Failure -> {
                    binding.tvMessage.text = response.message
                }
            }
        }

    }

    private fun loadImages(images: List<Image>) {
        binding.vpImages.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpImages.adapter = ImagesAdapter(images)

        // show current image
        if (args.imageId != -1L) {
            images.forEachIndexed { index, image ->
                if (args.imageId == image.id) {
                    binding.vpImages.currentItem = index
                    return@forEachIndexed
                }
            }
        }

    }

}