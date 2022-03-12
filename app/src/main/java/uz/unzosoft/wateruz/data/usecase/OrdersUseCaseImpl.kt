package uz.unzosoft.wateruz.data.usecase

import kotlinx.coroutines.flow.Flow
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.domain.repository.OrdersRepository
import uz.unzosoft.wateruz.domain.usecase.OrdersUseCase
import uz.unzosoft.wateruz.domain.utils.ResourceUI
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:58.
 * company QQBank
 * shahzod9933@gmail.com
 */
class OrdersUseCaseImpl @Inject constructor(
    private val repository: OrdersRepository,
) : OrdersUseCase {
    override suspend fun invoke(): Flow<ResourceUI<OrdersResponse>> {
        return repository.orders()
    }
}