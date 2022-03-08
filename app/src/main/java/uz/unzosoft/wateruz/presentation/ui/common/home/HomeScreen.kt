package uz.unzosoft.wateruz.presentation.ui.common.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.unzosoft.wateruz.R
import uz.unzosoft.wateruz.databinding.ScreenHomeBinding
import uz.unzosoft.wateruz.presentation.ui.base.BaseScreen

@AndroidEntryPoint
class HomeScreen : BaseScreen(R.layout.screen_home) {
    override val viewModel: HomeVM by viewModels()
    private val binding by viewBinding(ScreenHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}