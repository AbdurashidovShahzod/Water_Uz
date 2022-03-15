package uz.unzosoft.wateruz.presentation.ui.common.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import uz.unzosoft.wateruz.data.local.LocalStorage
import uz.unzosoft.wateruz.data.models.api.OrdersItem
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.domain.usecase.HomeUseCase
import uz.unzosoft.wateruz.domain.usecase.OrdersUseCase
import uz.unzosoft.wateruz.domain.utils.ResourceUI
import uz.unzosoft.wateruz.presentation.ui.base.BaseVM
import uz.unzosoft.wateruz.presentation.ui.state.Resource
import javax.inject.Inject


/**
 * Created by Abdurashidov Shahzod on 06/03/22 18:07.
 * company QQBank
 * shahzod9933@gmail.com
 */
@HiltViewModel
class HomeVM @Inject constructor(
    private val cache: LocalStorage,
    private val ordersUseCase: OrdersUseCase,
    private val homeUseCase: HomeUseCase
) : BaseVM() {
    private val _ordersLiveData = MutableLiveData<List<OrdersItem>>()
    val ordersLiveData: LiveData<List<OrdersItem>> = _ordersLiveData

    private val _orderState = MutableStateFlow<List<OrdersItem>>(emptyList())
    val ordersState: StateFlow<List<OrdersItem>> = _orderState.asStateFlow()

    init {
        home()
    }

//    fun orders() {
//        launchVM {
//            ordersUseCase.invoke().collect {
//                when (it) {
//                    is ResourceUI.Loading -> {
//                        _loadingLiveData.value = Unit
//                    }
//                    is ResourceUI.Error -> {
//                        globalError(it.error.toString())
//                    }
//                    is ResourceUI.Resource -> {
//                        val data = it.data
//                        Timber.d("zannigor", data.toString())
//                        _ordersLiveData.value = data
//                    }
//                }
//            }
//        }
//    }

    fun home() {
        launchVM {
            homeUseCase.invoke().collect {
                when (it) {
                    is Resource.Error -> {
                        globalError(it.message.toString())
                    }
                    is Resource.Success -> {
                        val data = it.data
                        _ordersLiveData.value = data!!
                        _orderState.value = data
                    }
                    else -> {}
                }
            }
        }
    }
}