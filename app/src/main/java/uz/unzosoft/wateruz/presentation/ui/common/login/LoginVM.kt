package uz.unzosoft.wateruz.presentation.ui.common.login

import dagger.hilt.android.lifecycle.HiltViewModel
import uz.unzosoft.wateruz.data.local.LocalStorage
import uz.unzosoft.wateruz.domain.usecase.LoginUseCase
import uz.unzosoft.wateruz.presentation.ui.base.BaseVM
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

    init {
        launchVM {

        }
    }
}