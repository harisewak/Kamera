package com.harisewak.kamera.save_image_feature

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.harisewak.kamera.R
import com.harisewak.kamera.databinding.FragmentSaveImageBinding
import com.harisewak.kamera.others.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class SaveImageFragment : Fragment() {
    private lateinit var binding: FragmentSaveImageBinding
    private val viewModel: SaveImageViewModel by viewModels()
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService
    private val TAG = "SaveImageFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveImageBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isCameraPermissionGranted()) {
            startCamera()
        } else {
            requestCameraPermission()
        }

        binding.btTakePhoto.setOnClickListener {
            if (isCameraPermissionGranted()) {
                takePhoto()
            } else {
                requestCameraPermission()
            }
        }

    }

    private fun takePhoto() {

        val imageCapture = imageCapture ?: return

        imageCapture.takePicture(
            getOutputOptions(),
            ContextCompat.getMainExecutor(requireContext()),

            object : ImageCapture.OnImageSavedCallback {

                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                    requireContext().showToast(getString(R.string.msg_image_capture_failed))
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {

                    lifecycleScope.launch {

                        val response = viewModel.saveImage(output.savedUri.toString())

                        if (isVisible) {
                            when (response) {
                                is SaveImageResponse.Success -> {
                                    binding.ivPreview.setImageURI(Uri.parse(response.uri))
                                }
                                is SaveImageResponse.Failure -> {
                                    binding.root.showSnackbar(response.message)
                                }
                            }
                        }

                    }
                }
            })
    }

    private fun getOutputOptions() = ImageCapture.OutputFileOptions.Builder(
        requireContext().contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        getContentValues()
    ).build()

    private fun getContentValues(): ContentValues {
        val contentValues = ContentValues()
        val timeStamp = System.currentTimeMillis()

        contentValues.put(
            MediaStore.MediaColumns.DISPLAY_NAME,
            "${Constants.IMAGE_NAME_PREFIX}${timeStamp}"
        )

        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, Constants.MIME_TYPE_IMAGE)

        return contentValues
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
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
                startCamera()
            },
            showPermissionRequired = {
                context?.showToast(getString(R.string.camera_permission_required))
                requireActivity().finish()
            }
        )

    }

}