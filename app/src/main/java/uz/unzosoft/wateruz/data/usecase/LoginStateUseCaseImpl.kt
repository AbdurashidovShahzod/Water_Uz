package uz.unzosoft.wateruz.data.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.domain.repository.LoginRepository
import uz.unzosoft.wateruz.domain.repository.LoginRepositoryState
import uz.unzosoft.wateruz.domain.usecase.LoginStateUseCase
import uz.unzosoft.wateruz.domain.usecase.LoginUseCase
import uz.unzosoft.wateruz.domain.utils.ResourceUI
import uz.unzosoft.wateruz.presentation.ui.state.Resource
import java.io.IOException
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:58.
 * company QQBank
 * shahzod9933@gmail.com
 */
class LoginStateUseCaseImpl @Inject constructor(
    private val repository: LoginRepositoryState
) : LoginStateUseCase {
    override suspend fun invoke(loginRequest: LoginRequest): Flow<ResourceUI<LoginResponse>> {
        return repository.loginState(loginRequest)
    }
}