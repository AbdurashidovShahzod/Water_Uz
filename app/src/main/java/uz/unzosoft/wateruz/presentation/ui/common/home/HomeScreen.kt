package uz.unzosoft.wateruz.presentation.ui.common.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.unzosoft.wateruz.R
import uz.unzosoft.wateruz.data.local.LocalStorage
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.databinding.ScreenHomeBinding
import uz.unzosoft.wateruz.presentation.ui.base.BaseScreen
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : BaseScreen(R.layout.screen_home) {
    override val viewModel: HomeVM by viewModels()
    private val binding by viewBinding(ScreenHomeBinding::bind)

    @Inject
    lateinit var cache: LocalStorage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        statusBarColor()
        setupUi()
        viewModel.apply {
            ordersLiveData.observe(viewLifecycleOwner, ordersObserver)
            loadingLiveData.observe(viewLifecycleOwner,loadingObserver)
        }
    }

    private fun setupUi() {
        binding.apply {
            appName.text = cache.userName
        }
    }

    private val ordersObserver = Observer<OrdersResponse> {

    }
    private val loadingObserver = Observer<Unit> {

    }
}