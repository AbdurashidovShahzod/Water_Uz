package uz.unzosoft.wateruz.data.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.data.utils.*
import uz.unzosoft.wateruz.domain.repository.OrdersRepository
import uz.unzosoft.wateruz.domain.usecase.OrdersUseCase
import uz.unzosoft.wateruz.presentation.ui.utils.ResourceUI
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:58.
 * company QQBank
 * shahzod9933@gmail.com
 */
class OrdersUseCaseImpl @Inject constructor(
    private val repository: OrdersRepository
) : OrdersUseCase {

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

    override suspend fun invoke(): Flow<ResourceUI<OrdersResponse>> {
        return flow {
            val data = try {
                emit(ResourceUI.Loading)
                val orders = repository.orders()
                val d = orders.body()
                when (orders.code()) {
                    in 200..299 -> {
                        if (d != null) ResourceUI.Resource(d, orders.code())
                        else ResourceUI.Error(NullPointerException("Response data must not be null"))
                    }
                    401 -> {
                        ResourceUI.Error(TokenWrongException(""))
                    }
                    428 -> {
                        val message: String = handleError(orders.errorBody())
                        ResourceUI.Error(GlobalException(message, orders.code()))
                    }
                    426 -> {
                        val message: String = handleError(orders.errorBody())
                        ResourceUI.Error(AppUpdateException(message))
                    }
                    422 -> {
                        val message: String = handleError(orders.errorBody())
                        ResourceUI.Error(InfoForUser(message))
                    }
                    else -> {
                        val message: String = handleError(orders.errorBody())
                        ResourceUI.Error(NetworkException(message))
                    }
                }
            } catch (e: Exception) {
                when (e) {
                    else -> ResourceUI.Error(e)
                }
            }
            when (data) {
                is ResourceUI.Error -> {
                    emit(data)
                }
                is ResourceUI.Resource -> {
                    val resource = data.data
                    emit(ResourceUI.Resource(resource))
                }
                else -> {}
            }
        }.flowOn(Dispatchers.IO)
    }
}
