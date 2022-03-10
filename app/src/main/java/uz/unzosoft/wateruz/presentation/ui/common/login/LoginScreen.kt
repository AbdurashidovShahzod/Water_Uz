package uz.unzosoft.wateruz.presentation.ui.common.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import uz.unzosoft.wateruz.R
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.databinding.ScreenLoginBinding
import uz.unzosoft.wateruz.presentation.ui.base.BaseScreen
import uz.unzosoft.wateruz.presentation.ui.utils.context.inVisible
import uz.unzosoft.wateruz.presentation.ui.utils.context.toast
import uz.unzosoft.wateruz.presentation.ui.utils.context.visible

@AndroidEntryPoint
class LoginScreen : BaseScreen(R.layout.screen_login), View.OnClickListener {

    override val viewModel: LoginVM by viewModels()
    private val binding by viewBinding(ScreenLoginBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        statusBarColor()
        viewModel.apply {
            loginLiveData.observe(viewLifecycleOwner, loginObserver)
            connectLiveData.observe(viewLifecycleOwner, connectObserver)
            loadingLiveData.observe(viewLifecycleOwner, loadingObserver)
            nextScreenLiveData.observe(viewLifecycleOwner, nextScreenObserver)
        }
        binding.signIn.setOnClickListener(this)
    }

    private val loginObserver = Observer<LoginResponse> {

    }
    private val connectObserver = Observer<Boolean> {
        view?.let { it1 ->
            Snackbar.make(it1, "Internet bilan aloqa yo'q", Snackbar.LENGTH_SHORT).show()
        }
    }
    private val loadingObserver = Observer<Unit> {
        binding.progressBar.inVisible()
    }
    private val nextScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_loginScreen_to_homeScreen)
    }

    override
    fun onClick(v: View?) {
        when (v?.id) {
            R.id.signIn -> {
                viewModel.login(
                    binding.editEmail.text.toString(),
                    binding.editPassword.text.toString()
                )
                binding.progressBar.visible()
            }
        }
    }

}