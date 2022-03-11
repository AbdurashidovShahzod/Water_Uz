package uz.unzosoft.wateruz.domain.usecase

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.presentation.ui.state.Resource
import uz.unzosoft.wateruz.presentation.ui.utils.ResourceUI


/**
 * Created by Abdurashidov Shahzod on 11/03/22 16:09.
 * company QQBank
 * shahzod9933@gmail.com
 */
interface OrdersUseCase {
    suspend fun invoke(): Flow<ResourceUI<OrdersResponse>>
}