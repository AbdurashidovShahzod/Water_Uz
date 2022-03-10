package uz.unzosoft.wateruz.presentation.ui.utils.context

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable


inline fun <reified T : Activity> Context.newIntent(vararg pairs: Pair<String, Any?>): Intent {
    return Intent(this, T::class.java).apply {
        setArguments(*pairs)
    }
}

inline fun <reified T : Activity> Context.newIntent(flags: Int, vararg pairs: Pair<String, Any?>): Intent {
    return Intent(this, T::class.java).apply {
        this.flags = flags
        this.setArguments(*pairs)
    }
}

inline fun <reified A : Context> A.newIntent(clazzName: String, vararg pairs: Pair<String, Any?>): Intent {
    return Intent().setClassName(packageName, clazzName).apply {
        setArguments(*pairs)
    }
}

inline fun <reified A : Context> A.newIntent(clazzName: String, flags: Int, vararg pairs: Pair<String, Any?>): Intent {
    return Intent().setClassName(packageName, clazzName).apply {
        this.flags = flags
        this.setArguments(*pairs)
    }
}

fun Intent.setArguments(vararg pairs: Pair<String, Any?>) {
    for ((key, value) in pairs) {
        when (value) {
            null -> putExtra(key, null as? String?)

            // Scalars
            is Boolean -> putExtra(key, value)
            is Byte -> putExtra(key, value)
            is Char -> putExtra(key, value)
            is Double -> putExtra(key, value)
            is Float -> putExtra(key, value)
            is Int -> putExtra(key, value)
            is Long -> putExtra(key, value)
            is Short -> putExtra(key, value)
            is String -> putExtra(key, value)

            // References
            is Bundle -> putExtra(key, value)
            is CharSequence -> putExtra(key, value)
            is Parcelable -> putExtra(key, value)

            // Scalar arrays
            is BooleanArray -> putExtra(key, value)
            is ByteArray -> putExtra(key, value)
            is CharArray -> putExtra(key, value)
            is DoubleArray -> putExtra(key, value)
            is FloatArray -> putExtra(key, value)
            is IntArray -> putExtra(key, value)
            is LongArray -> putExtra(key, value)
            is ShortArray -> putExtra(key, value)
            is Serializable -> putExtra(key, value)

            else -> throw IllegalArgumentException("Illegal value $value for key \"$key\"")
        }
    }
}