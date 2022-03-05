package uz.unzosoft.wateruz.presentation.ui.utils

import android.os.SystemClock
import android.view.View


abstract class SingleClickListener : View.OnClickListener {

    companion object {
        private const val MIN_CLICK_INTERVAL: Long = 2000
        private var mLastClickTime: Long = 0
    }

    abstract fun onSingleClick(v: View?)

    override fun onClick(v: View?) {
        if (mLastClickTime <= 0) {
            mLastClickTime = SystemClock.uptimeMillis()
            onSingleClick(v)
            return
        }

        if (SystemClock.uptimeMillis() - mLastClickTime <= MIN_CLICK_INTERVAL) {
            return
        }

        mLastClickTime = SystemClock.uptimeMillis()

        onSingleClick(v)
    }
}