package uz.unzosoft.wateruz.domain.usecase

import retrofit2.Response
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:57.
 * company QQBank
 * shahzod9933@gmail.com
 */
interface LoginUseCase {
    suspend fun invoke(loginRequest: LoginRequest): Response<LoginResponse>
}