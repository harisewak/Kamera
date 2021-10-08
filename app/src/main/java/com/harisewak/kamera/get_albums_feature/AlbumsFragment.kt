package com.harisewak.kamera.get_albums_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.harisewak.kamera.R
import com.harisewak.kamera.data.Album
import com.harisewak.kamera.databinding.FragmentAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment() {
    private lateinit var binding: FragmentAlbumsBinding
    private val viewModel: GetAlbumsViewModel by viewModels()
    private val TAG = "GetAlbumsFragment"
    private val adapter = AlbumsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeData()

    }

    private fun setListeners() {

        binding.ivCamera.setOnClickListener {
            findNavController()
                .navigate(
                    AlbumsFragmentDirections
                        .actionAlbumsFragmentToSaveImageFragment()
                )
        }

        adapter.setItemClickListener {
            findNavController()
                .navigate(
                    AlbumsFragmentDirections
                        .actionAlbumsFragmentToImagesFragment(
                            albumId = it.id!!
                        )
                )
        }
    }

    private fun observeData() {
        viewModel.albums.observe(viewLifecycleOwner) { response ->

            when (response) {

                is GetAlbumsResponse.Success -> {
                    if (response.albums.isEmpty()) {
                        binding.tvMessage.text = getString(R.string.msg_no_albums)
                    } else {
                        binding.tvMessage.visibility = View.GONE
                        loadAlbums(response.albums)
                    }
                }

                is GetAlbumsResponse.Failure -> {
                    // show message
                    binding.tvMessage.text = response.message
                }
            }
        }
    }

    private fun loadAlbums(albums: List<Album>) {
        binding.rvAlbums.adapter = adapter
        adapter.submitList(albums)
    }


}