package uz.unzosoft.wateruz.domain.usecase

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.domain.utils.ResourceUI
import uz.unzosoft.wateruz.presentation.ui.state.Resource


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:57.
 * company QQBank
 * shahzod9933@gmail.com
 */
interface LoginStateUseCase {
    suspend fun invoke(loginRequest: LoginRequest): Flow<ResourceUI<LoginResponse>>
}