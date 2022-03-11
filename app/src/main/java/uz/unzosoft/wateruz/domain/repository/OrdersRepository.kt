package uz.unzosoft.wateruz.domain.repository

import retrofit2.Response
import uz.unzosoft.wateruz.data.models.api.OrdersResponse


/**
 * Created by Abdurashidov Shahzod on 11/03/22 16:07.
 * company QQBank
 * shahzod9933@gmail.com
 */
interface OrdersRepository {
    suspend fun orders():Response<OrdersResponse>
}