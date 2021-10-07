package com.harisewak.kamera.others

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

const val TAG = "PermissionsExt"
const val cameraPermission = Manifest.permission.CAMERA
const val permReqCode = 111


fun Fragment.isCameraPermissionGranted(): Boolean {

    context?.let {
        return ContextCompat.checkSelfPermission(
            it,
            cameraPermission
        ) == PackageManager.PERMISSION_GRANTED
    }

    return false
}

@Suppress("deprecation")
fun Fragment.requestCameraPermission() =
    requestPermissions(
        arrayOf(cameraPermission),
        permReqCode
    )

fun handleRequestPermissionsResult(
    requestCode: Int,
    grantResults: IntArray,
    onPermissionGranted: () -> Unit,
    showPermissionRequired: () -> Unit
) {
    if (requestCode == permReqCode ||
        grantResults[0] == PackageManager.PERMISSION_GRANTED
    ) {
        onPermissionGranted()
    } else {
        showPermissionRequired()
    }
}


