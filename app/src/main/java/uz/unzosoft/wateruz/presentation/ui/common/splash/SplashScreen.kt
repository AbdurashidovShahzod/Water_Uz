package uz.unzosoft.wateruz.presentation.ui.common.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.unzosoft.wateruz.presentation.ui.common.base.BaseScreen
import uz.unzosoft.wateruz.R
import uz.unzosoft.wateruz.databinding.ScreenLoginBinding
import uz.unzosoft.wateruz.databinding.ScreenSplashBinding
import uz.unzosoft.wateruz.presentation.ui.common.base.BaseVM


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : BaseScreen(R.layout.screen_splash) {
    override val viewModel: SplashVM by viewModels()
    private val binding by viewBinding(ScreenLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}