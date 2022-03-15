package uz.unzosoft.wateruz.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.domain.utils.ResourceUI


/**
 * Created by Abdurashidov Shahzod on 15/03/22 02:41.
 * company QQBank
 * shahzod9933@gmail.com
 */
interface LoginRepositoryState {
    suspend fun loginState(loginRequest: LoginRequest): Flow<ResourceUI<LoginResponse>>
}