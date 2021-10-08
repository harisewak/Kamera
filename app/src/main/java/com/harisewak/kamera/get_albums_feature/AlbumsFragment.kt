package com.harisewak.kamera.get_albums_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.harisewak.kamera.databinding.FragmentAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment() {
    private lateinit var binding: FragmentAlbumsBinding
    private val viewModel: GetAlbumsViewModel by viewModels()
    private val TAG = "GetAlbumsFragment"

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

        viewModel.albums.observe(viewLifecycleOwner) { response ->

            when (response) {

                is GetAlbumsResponse.Success -> {
                    if (response.albums.isEmpty()) {
                        // no albums
                    } else {
                        // show albums
                    }
                }

                is GetAlbumsResponse.Failure -> {
                    // show message
                }
            }
        }

    }


}