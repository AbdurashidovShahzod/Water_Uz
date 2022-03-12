package uz.unzosoft.wateruz.data.utils

import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import uz.unzosoft.wateruz.domain.utils.ResourceUI
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException


/**
 * Created by Abdurashidov Shahzod on 12/03/22 20:36.
 * company QQBank
 * shahzod9933@gmail.com
 */

@DslMarker
annotation class WrapperDsl

class ResponseWrapper @Inject constructor() {

    suspend fun <T, E> wrapperListX(
        mapper: Mapper<T, E>,
        body: suspend () -> Response<BaseResponse<List<E>>>
    ): ResourceUI<List<T>> {
        return try {
            val response: Response<BaseResponse<List<E>>> = body()
            checkStatusList(mapper, response)
        } catch (e: Exception) {
            when (e) {
                is NullPointerException -> ResourceUI.Error(Exception("Data is Empty"))
                is UnknownHostException -> ResourceUI.Error(ServerException("Not connection with server"))
                is SSLHandshakeException -> ResourceUI.Error(ServerException("Not connection with server"))
                is SSLException -> ResourceUI.Error(ServerException("Not connection with server"))
                is SocketException -> ResourceUI.Error(SocketException("Not connection with server"))
//            else -> Error(Exception("Not connection with server."))
                else -> ResourceUI.Error(e)
            }
        }
    }

    private fun <T, E> checkStatusList(
        mapper: Mapper<T, E>,
        response: Response<BaseResponse<List<E>>>
    ): ResourceUI<List<T>> {
        val body: BaseResponse<List<E>>? = response.body()
        val data: List<T>? = body?.data?.let { list -> list.map { mapper.mapData(it) } }
        return when (response.code()) {
            in 200..299 -> {
                if (data != null) ResourceUI.Resource(data, response.code())
                else ResourceUI.Error(NullPointerException("Response data must not be null"))
            }
            401 -> {
                ResourceUI.Error(TokenWrongException(""))
            }
            428 -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(GlobalException(message, response.code()))
            }
            426 -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(AppUpdateException(message))
            }
            422 -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(InfoForUser(message))
            }
            else -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(NetworkException(message))
            }
        }
    }

    suspend fun <T, E> wrapperX(
        mapper: Mapper<T, E>,
        body: suspend () -> Response<BaseResponse<E>>
    ): ResourceUI<T> {
        return try {
            val response: Response<BaseResponse<E>> = body()
            checkStatus(mapper, response)
        } catch (e: Exception) {
            when (e) {
//                is NullPointerException -> ResourceUI.Error(Exception("Data is Empty"))
//                is UnknownHostException -> ResourceUI.Error(ServerException("Not connection with server"))
//                is SSLHandshakeException -> ResourceUI.Error(ServerException("Not connection with server"))
//                is SSLException -> ResourceUI.Error(ServerException("Not connection with server"))
//                is SocketException -> ResourceUI.Error(SocketException("Not connection with server"))
//            else -> ResourceUI.Error(Exception("Not connection with server."))
                else -> ResourceUI.Error(e)
            }
        }
    }

    private fun <T, E> checkStatus(
        mapper: Mapper<T, E>,
        response: Response<BaseResponse<E>>
    ): ResourceUI<T> {
        val body: BaseResponse<E>? = response.body()
        val data: T? = body?.data?.let { mapper.mapData(it) }
        return when (response.code()) {
            in 200..299 -> {
                if (data != null) ResourceUI.Resource(data, response.code())
                else ResourceUI.Error(NullPointerException("Response data must not be null"))
            }
            401 -> {
                ResourceUI.Error(TokenWrongException(""))
            }
            428 -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(GlobalException(message, response.code()))
            }
            426 -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(AppUpdateException(message))
            }
            422 -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(InfoForUser(message))
            }
            else -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(NetworkException(message))
            }
        }
    }

    suspend fun <T, E> wrapperX(
        mapper: (dto: E) -> T,
        body: suspend () -> Response<BaseResponse<E>>
    ): ResourceUI<T> {
        return try {
            val response: Response<BaseResponse<E>> = body()
            checkStatus(mapper, response)
        } catch (e: Exception) {
            when (e) {
//                is NullPointerException -> ResourceUI.Error(Exception("Data is Empty"))
//                is UnknownHostException -> ResourceUI.Error(ServerException("Not connection with server"))
//                is SSLHandshakeException -> ResourceUI.Error(ServerException("Not connection with server"))
//                is SSLException -> ResourceUI.Error(ServerException("Not connection with server"))
//                is SocketException -> ResourceUI.Error(SocketException("Not connection with server"))
//            else -> ResourceUI.Error(Exception("Not connection with server."))
                else -> ResourceUI.Error(e)
            }
        }
    }

    private fun <T, E> checkStatus(
        mapper: (dto: E) -> T,
        response: Response<BaseResponse<E>>
    ): ResourceUI<T> {
        val body: BaseResponse<E>? = response.body()
        val data: T? = body?.data?.let { mapper.invoke(it) }
        return when (response.code()) {
            in 200..299 -> {
                val errorMessage = body?.errorMessage
                when {
                    errorMessage.isNullOrEmpty() -> {
                        ResourceUI.Error(NullPointerException(errorMessage))
                    }
                    data == null -> {
                        ResourceUI.Error(NullPointerException("Response data must not be null"))
                    }
                    else -> ResourceUI.Resource(data, response.code())
                }
            }
            401 -> {
                ResourceUI.Error(TokenWrongException(""))
            }
            428 -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(GlobalException(message, response.code()))
            }
            426 -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(AppUpdateException(message))
            }
            422 -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(InfoForUser(message))
            }
            else -> {
                val message: String = handleError(response.errorBody())
                ResourceUI.Error(NetworkException(message))
            }
        }
    }

    private fun handleError(body: ResponseBody?): String {
        body?.toString()

        val tempError = """{ "errorMessage" = "Some Error from network" }"""
//        val byteArray: ByteArray = body?.bytes() ?: tempError.toByteArray()
        val byteArray: ByteArray = body?.bytes() ?: tempError.toByteArray()
        return try {
            JSONObject(String(byteArray)).getString("errorMessage")
        } catch (e: JSONException) {
//            "Some Error from network"
            e.message.toString()
        }
    }
}