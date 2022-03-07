package uz.unzosoft.wateruz.presentation.ui.common.splash

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import timber.log.Timber
import uz.unzosoft.wateruz.data.local.LocalStorage
import uz.unzosoft.wateruz.presentation.ui.common.base.BaseVM
import uz.unzosoft.wateruz.presentation.ui.utils.vm.SingleLiveEvent
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 06/03/22 18:07.
 * company QQBank
 * shahzod9933@gmail.com
 */
@HiltViewModel
class SplashVM @Inject constructor(
    private val appCache: LocalStorage
) : BaseVM() {

    private val _homeScreenLiveData = SingleLiveEvent<Unit>()
    val homeScreenLiveData: LiveData<Unit> = _homeScreenLiveData

    private val _loginScreenLiveData = SingleLiveEvent<Unit>()
    val loginScreenLiveData: LiveData<Unit> = _loginScreenLiveData

    init {
        launchVM {
            Timber.d(appCache.token.toString(),"ZANNI")
            delay(1000)
            if (appCache.token.isNullOrBlank())
                _loginScreenLiveData.value = Unit
            else
                _homeScreenLiveData.value = Unit
        }
    }
}