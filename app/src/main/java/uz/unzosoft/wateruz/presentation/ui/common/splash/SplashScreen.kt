package uz.unzosoft.wateruz.presentation.ui.common.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.unzosoft.wateruz.R
import uz.unzosoft.wateruz.databinding.ScreenSplashBinding
import uz.unzosoft.wateruz.presentation.ui.base.BaseScreen


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : BaseScreen(R.layout.screen_splash) {
    override val viewModel: SplashVM by viewModels()
    private val binding by viewBinding(ScreenSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        statusBarColor()
        viewModel.apply {
            homeScreenLiveData.observe(viewLifecycleOwner, homeScreenObserver)
            loginScreenLiveData.observe(viewLifecycleOwner, loginScreenObserver)
        }
        binding.imageSplash.animate()
            .translationX(0F)
            .alpha(1F)
            .setDuration(1000)
            .start()
    }

    private val homeScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
    }

    private val loginScreenObserver = Observer<Unit> {
        //findNavController().navigate(R.id.action_splashScreen_to_loginScreen)
        findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
    }
}