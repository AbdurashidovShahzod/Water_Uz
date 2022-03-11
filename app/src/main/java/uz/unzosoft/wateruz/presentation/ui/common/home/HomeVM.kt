package uz.unzosoft.wateruz.presentation.ui.common.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import uz.unzosoft.wateruz.data.local.LocalStorage
import uz.unzosoft.wateruz.data.models.api.OrdersItem
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.domain.usecase.OrdersUseCase
import uz.unzosoft.wateruz.presentation.ui.base.BaseVM
import uz.unzosoft.wateruz.presentation.ui.utils.ResourceUI
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 06/03/22 18:07.
 * company QQBank
 * shahzod9933@gmail.com
 */
@HiltViewModel
class HomeVM @Inject constructor(
    private val cache: LocalStorage,
    private val ordersUseCase: OrdersUseCase
) : BaseVM() {
    private val _ordersLiveData = MutableLiveData<OrdersResponse>()
    val ordersLiveData: LiveData<OrdersResponse> = _ordersLiveData

    init {
        orders()
    }

    private fun orders() {
        launchVM {
            ordersUseCase.invoke().collect {
                when (it) {
                    is ResourceUI.Loading -> {
                        _loadingLiveData.value = Unit
                    }
                    is ResourceUI.Error -> {
                        globalError(it.error.toString())
                    }
                    is ResourceUI.Resource -> {
                        val data = it.data
                        Timber.d("zannigor", data.toString())
                        _ordersLiveData.value = data
                    }
                }
            }
        }
    }
}