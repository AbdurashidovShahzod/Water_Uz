package uz.unzosoft.wateruz.presentation.ui.utils.context

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Activity.hideKeyboard() {
    currentFocus?.let { view ->
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

private fun setWindowFlag(bits: Int, on: Boolean, window: Window) {
    val winParams = window.attributes
    if (on) {
        winParams.flags = winParams.flags or bits
    } else {
        winParams.flags = winParams.flags and bits.inv()
    }
    window.attributes = winParams
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Activity.darkStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.navigationBarColor = Color.BLACK
    }
}

private fun containsFlag(flags: Int, flagToCheck: Int) = (flags and flagToCheck) != 0

private fun removeFlag(flags: Int, flagToRemove: Int): Int {
    return if (containsFlag(flags, flagToRemove)) {
        flags and flagToRemove.inv()
    } else {
        flags
    }
}


fun Window.transparentStatusBar() {
    setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true, this)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    if (Build.VERSION.SDK_INT >= 21) {
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false, this)
        this.statusBarColor = Color.TRANSPARENT
    }
}
fun View.toast(mes: Any?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, mes?.toString(), duration).show()
}


fun Activity.hideSystemUI() {
    window.decorView.apply {
        systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE
    }
}

fun Activity.showSystemUI() {
    window.decorView.apply {
        systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}


fun Fragment.showSnackBar(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
}
