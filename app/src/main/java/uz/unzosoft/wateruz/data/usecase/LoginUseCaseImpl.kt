package uz.unzosoft.wateruz.data.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.domain.repository.LoginRepository
import uz.unzosoft.wateruz.domain.usecase.LoginUseCase
import uz.unzosoft.wateruz.presentation.ui.state.Resource
import java.io.IOException
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:58.
 * company QQBank
 * shahzod9933@gmail.com
 */
class LoginUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
) : LoginUseCase {
    override suspend fun invoke(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        val data = repository.login(loginRequest)
        return flow {
            try {
                emit(Resource.Loading<LoginResponse>())
                if (data.code() == 200) data.body().let {
                    emit(Resource.Success<LoginResponse>(it!!))
                } else {
                    emit(Resource.Error(data.message()))
                }
            } catch (e: HttpException) {
                emit(Resource.Error(handleError(data.errorBody())))
            } catch (e: IOException) {
                emit(Resource.Error(handleError(data.errorBody())))
            }
        }
    }

    private fun handleError(body: ResponseBody?): String {
        body?.toString()
        val tempError = """{ "errorMessage" = "Some Error from network" }"""
        val byteArray: ByteArray = body?.bytes() ?: tempError.toByteArray()
        return try {
            JSONObject(String(byteArray)).getString("errorMessage")
        } catch (e: JSONException) {
            e.message.toString()
        }
    }


}