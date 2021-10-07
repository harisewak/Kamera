package com.harisewak.kamera.others

sealed class SaveImageResponse(
    open val uri: String? = null,
    open val message: String? = null
)

class Success(override val uri: String): SaveImageResponse(uri)
class Failure(override val message: String): SaveImageResponse(message)
