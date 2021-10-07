package com.harisewak.kamera.others

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    val snack: Snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    gravityTop(snack)
    snack.show()
}

fun View.showSnackbarWithRetry(message: String, retry: () -> Unit) {
    val snack: Snackbar = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    gravityTop(snack)
    snack.setAction(message) {
        retry.invoke()
    }
    snack.show()
}

private fun gravityTop(snack: Snackbar) {
    val view = snack.view
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
}

fun View.hideKeyboard() {
    val inputMethodManager =
        this.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}
