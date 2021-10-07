package com.harisewak.kamera.save_image_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.harisewak.kamera.R
import com.harisewak.kamera.others.handleRequestPermissionsResult
import com.harisewak.kamera.others.isCameraPermissionGranted
import com.harisewak.kamera.others.requestCameraPermission
import com.harisewak.kamera.others.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveImageFragment: Fragment() {
    private val viewModel: SaveImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // todo write inflate code
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isCameraPermissionGranted()) {
            // todo initialize camera and other components
        } else {
            requestCameraPermission()
        }
    }

    @Suppress("deprecation")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        handleRequestPermissionsResult(
            requestCode,
            grantResults,
            onPermissionGranted = {
                // todo initialize camera & other components
            },
            showPermissionRequired = {
                context?.showToast(getString(R.string.camera_permissions_required))
                requireActivity().finish()
            }
        )

    }

}