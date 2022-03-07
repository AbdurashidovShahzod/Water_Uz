package uz.unzosoft.wateruz.data.local

import android.content.Context
import android.content.SharedPreferences
import uz.unzosoft.wateruz.presentation.ui.utils.BooleanPreference
import uz.unzosoft.wateruz.presentation.ui.utils.StringPreference


class LocalStorage(context: Context) {

    companion object {
        @Volatile
        lateinit var instance: LocalStorage
            private set

        fun init(context: Context) {
            synchronized(this) {
                instance = LocalStorage(context)
            }
        }
    }

    private val pref: SharedPreferences =
        context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)

    var logged: Boolean by BooleanPreference(pref, false)
    var completeIntro: Boolean by BooleanPreference(pref, false)
    var token: String by StringPreference(pref,"")
    var refreshToken: String by StringPreference(pref)
    var id: String by StringPreference(pref)
    var lan: String by StringPreference(pref)
    var markedLanguage: String by StringPreference(pref)
    var isOnBoarding: Boolean by BooleanPreference(pref, false)


}
