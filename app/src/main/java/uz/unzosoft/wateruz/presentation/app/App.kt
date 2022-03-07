package uz.unzosoft.wateruz.presentation.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.unzosoft.wateruz.data.local.LocalStorage


/**
 * Created by Abdurashidov Shahzod on 06/03/22 01:36.
 * company QQBank
 * shahzod9933@gmail.com
 */
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        super.onCreate()
        LocalStorage.init(this)
        instance = this
    }

    companion object {
        lateinit var instance: App
    }

}