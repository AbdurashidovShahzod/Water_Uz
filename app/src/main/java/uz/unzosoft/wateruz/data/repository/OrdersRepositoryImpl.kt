package uz.unzosoft.wateruz.data.repository

import retrofit2.Response
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.data.remote.api.ApiService
import uz.unzosoft.wateruz.domain.repository.OrdersRepository
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 11/03/22 16:08.
 * company QQBank
 * shahzod9933@gmail.com
 */
class OrdersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : OrdersRepository {
    override suspend fun orders(): Response<OrdersResponse> {
        return apiService.orders()
    }
}