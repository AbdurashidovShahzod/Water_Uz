package uz.unzosoft.wateruz.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.unzosoft.wateruz.data.models.api.LoginRequest
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.data.models.api.OrdersItem
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.data.utils.BaseResponse


/**
 * Created by Abdurashidov Shahzod on 09/03/22 01:57.
 * company QQBank
 * shahzod9933@gmail.com
 */
interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("orders")
    suspend fun orders(): Response<BaseResponse<OrdersResponse>>

    @GET("orders")
    suspend fun home(): Response<List<OrdersItem>>
}