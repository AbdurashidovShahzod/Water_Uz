package uz.unzosoft.wateruz.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.data.remote.api.ApiService
import uz.unzosoft.wateruz.data.utils.BaseResponse
import uz.unzosoft.wateruz.data.utils.ResponseWrapper
import uz.unzosoft.wateruz.domain.repository.LoginRepository
import uz.unzosoft.wateruz.domain.repository.LoginRepositoryState
import uz.unzosoft.wateruz.domain.utils.ResourceUI
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:58.
 * company QQBank
 * shahzod9933@gmail.com
 */
class LoginStateRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val wrapper: ResponseWrapper
) : LoginRepositoryState {

    override suspend fun loginState(loginRequest: LoginRequest): Flow<ResourceUI<LoginResponse>> {
        return flow {
            val wrapperX = wrapper.wrapperX({ it }, { apiService.loginState(loginRequest) })
            emit(wrapperX)
        }.flowOn(Dispatchers.IO)
    }
}