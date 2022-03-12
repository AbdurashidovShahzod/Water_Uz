package uz.unzosoft.wateruz.domain.repository

import retrofit2.Response
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.data.models.api.OrdersItem
import uz.unzosoft.wateruz.data.models.api.OrdersResponse


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:57.
 * company QQBank
 * shahzod9933@gmail.com
 */
interface HomeRepository {
    suspend fun home(): Response<List<OrdersItem>>
}