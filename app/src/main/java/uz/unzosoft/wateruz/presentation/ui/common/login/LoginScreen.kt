package uz.unzosoft.wateruz.presentation.ui.common.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.unzosoft.wateruz.R
import uz.unzosoft.wateruz.databinding.ScreenLoginBinding
import uz.unzosoft.wateruz.presentation.ui.base.BaseScreen

@AndroidEntryPoint
class LoginScreen : BaseScreen(R.layout.screen_login) {

    override val viewModel: LoginVM by viewModels()
    private val binding by viewBinding(ScreenLoginBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        statusBarColor()
    }
}