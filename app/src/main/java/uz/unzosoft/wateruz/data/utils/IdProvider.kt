package uz.unzosoft.wateruz.data.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.unzosoft.wateruz.BuildConfig
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class IdProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {

    @SuppressLint("HardwareIds")
    fun getAppId(): String =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    fun getDeviceNameAndOSVersion() = Build.MODEL + " | Android " + Build.VERSION.RELEASE

    fun getDeviceMode(): String = when (BuildConfig.DEBUG) {
        true -> {
            "dev"
        }
        else -> {
            ""
        }
    }

    fun timestamp(): Long {
        return Date().time
    }

    @SuppressLint("HardwareIds")
    fun getAndroidId(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun getDeviceName(): String {
        return Build.MODEL
    }

    fun deviceType(): String {
        return "android"
    }

    @SuppressLint("HardwareIds")
    fun getDeviceID(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun getVersionName(): String {
        return getPackageInfo()?.versionName ?: ""
    }

    fun getVersionCode(): Int {
        return getPackageInfo()?.versionCode ?: 0
    }

    private fun getPackageInfo(): PackageInfo? {
        try {
            return context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

//    fun hasDeviceFingerprintSupport(): Boolean {
//        val fingerprintManager =
//            context.getSystemService(Context.FINGERPRINT_SERVICE) as? FingerprintManager
//                ?: return false
//        return if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.USE_FINGERPRINT
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            false
//        } else fingerprintManager.isHardwareDetected && fingerprintManager.hasEnrolledFingerprints()
//    }

}