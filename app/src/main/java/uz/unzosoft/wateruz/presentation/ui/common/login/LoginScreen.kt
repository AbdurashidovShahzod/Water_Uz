package uz.unzosoft.wateruz.presentation.ui.common.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.unzosoft.wateruz.R
import uz.unzosoft.wateruz.databinding.ScreenLoginBinding
import uz.unzosoft.wateruz.presentation.ui.common.base.BaseScreen
import uz.unzosoft.wateruz.presentation.ui.common.base.BaseVM
@AndroidEntryPoint
class LoginScreen : BaseScreen(R.layout.screen_login) {

    override val viewModel: LoginVM by viewModels()
    private val binding by viewBinding(ScreenLoginBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}