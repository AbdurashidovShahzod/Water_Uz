package uz.unzosoft.wateruz.data.repository

import retrofit2.Response
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.data.models.api.OrdersItem
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.data.remote.api.ApiService
import uz.unzosoft.wateruz.domain.repository.HomeRepository
import uz.unzosoft.wateruz.domain.repository.LoginRepository
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:58.
 * company QQBank
 * shahzod9933@gmail.com
 */
class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : HomeRepository {
    override suspend fun home(): Response<List<OrdersItem>> {
        return apiService.home()
    }
}