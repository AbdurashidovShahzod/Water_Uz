package uz.unzosoft.wateruz.data.usecase

import retrofit2.Response
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.data.remote.api.ApiService
import uz.unzosoft.wateruz.domain.repository.LoginRepository
import uz.unzosoft.wateruz.domain.usecase.LoginUseCase
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:58.
 * company QQBank
 * shahzod9933@gmail.com
 */
class LoginUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
) : LoginUseCase {
    override suspend fun invoke(loginRequest: LoginRequest): Response<LoginResponse> {
        return repository.login(loginRequest)
    }
}