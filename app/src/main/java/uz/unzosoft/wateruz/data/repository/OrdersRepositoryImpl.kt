package uz.unzosoft.wateruz.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.data.remote.api.ApiService
import uz.unzosoft.wateruz.data.utils.ResponseWrapper
import uz.unzosoft.wateruz.domain.repository.OrdersRepository
import uz.unzosoft.wateruz.domain.utils.ResourceUI
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 11/03/22 16:08.
 * company QQBank
 * shahzod9933@gmail.com
 */
class OrdersRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val wrapper: ResponseWrapper
) : OrdersRepository {
    override suspend fun orders(): Flow<ResourceUI<OrdersResponse>> {
        return flow {
            emit(wrapper.wrapperX({ it }, { apiService.orders() }))
        }.flowOn(Dispatchers.IO)
    }
}