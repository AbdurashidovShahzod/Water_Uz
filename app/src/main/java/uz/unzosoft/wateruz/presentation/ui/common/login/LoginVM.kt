package uz.unzosoft.wateruz.presentation.ui.common.login

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import uz.unzosoft.wateruz.data.local.LocalStorage
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.domain.usecase.LoginUseCase
import uz.unzosoft.wateruz.presentation.ui.base.BaseVM
import uz.unzosoft.wateruz.presentation.ui.state.Resource
import uz.unzosoft.wateruz.presentation.ui.utils.isConnected
import uz.unzosoft.wateruz.presentation.ui.utils.vm.SingleLiveEvent
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 06/03/22 18:07.
 * company QQBank
 * shahzod9933@gmail.com
 */
@HiltViewModel
class LoginVM @Inject constructor(
    private val cache: LocalStorage,
    private val loginUseCase: LoginUseCase
) : BaseVM() {

    private val _loginLiveData = MutableLiveData<LoginResponse>()
    val loginLiveData: LiveData<LoginResponse> = _loginLiveData
    private val _nextScreenLiveData = SingleLiveEvent<Unit>()
    val nextScreenLiveData:SingleLiveEvent<Unit> = _nextScreenLiveData


    @SuppressLint("NullSafeMutableLiveData")
    fun login(email: String, password: String) {
        _loadingLiveData.value = Unit
        launchVM {
            loginUseCase.invoke(LoginRequest(email = email, password = password)).collect {
                if (isConnected()) {
                    when (it) {
                        is Resource.Error -> {
                            globalError(it.message!!)
                        }
                        is Resource.Success -> {
                            val data = it.data
                            if (data != null) {
                                cache.token = data.accessToken!!
                                cache.userName = data.user!!.name.toString()
                                _loginLiveData.value = data
                                _nextScreenLiveData.value = Unit
                            }
                        }
                        else -> {}
                    }
                } else _connectLiveData.value = true

            }
        }
    }
}