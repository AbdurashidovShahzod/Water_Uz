package uz.unzosoft.wateruz.data.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.data.models.api.OrdersItem
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.domain.repository.HomeRepository
import uz.unzosoft.wateruz.domain.repository.OrdersRepository
import uz.unzosoft.wateruz.domain.usecase.HomeUseCase
import uz.unzosoft.wateruz.domain.usecase.OrdersUseCase
import uz.unzosoft.wateruz.domain.utils.ResourceUI
import uz.unzosoft.wateruz.presentation.ui.state.Resource
import java.io.IOException
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 09/03/22 17:58.
 * company QQBank
 * shahzod9933@gmail.com
 */
class HomeUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
) : HomeUseCase {
    override suspend fun invoke(): Flow<Resource<List<OrdersItem>>> {
        return flow {
            try {
                val data = repository.home()
                emit(Resource.Loading<List<OrdersItem>>())
                if (data.code() == 200) data.body().let {
                    emit(Resource.Success<List<OrdersItem>>(it!!))
                } else {
                    emit(Resource.Error(data.message()))
                }
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage))
            } catch (e: IOException) {
                emit(Resource.Error(e.message!!))
            }
        }.flowOn(Dispatchers.IO)
    }
}