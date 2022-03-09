package uz.unzosoft.wateruz.domain.repository

import retrofit2.Response
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:57.
 * company QQBank
 * shahzod9933@gmail.com
 */
interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse>
}