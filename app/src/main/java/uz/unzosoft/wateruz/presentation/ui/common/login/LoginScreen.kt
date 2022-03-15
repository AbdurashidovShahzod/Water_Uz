package uz.unzosoft.wateruz.presentation.ui.common.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import uz.unzosoft.wateruz.R
import uz.unzosoft.wateruz.data.models.api.LoginResponse
import uz.unzosoft.wateruz.databinding.ScreenLoginBinding
import uz.unzosoft.wateruz.domain.utils.ResourceUI
import uz.unzosoft.wateruz.presentation.ui.base.BaseScreen
import uz.unzosoft.wateruz.presentation.ui.utils.context.inVisible
import uz.unzosoft.wateruz.presentation.ui.utils.context.showSnackBar
import uz.unzosoft.wateruz.presentation.ui.utils.context.toast
import uz.unzosoft.wateruz.presentation.ui.utils.context.visible

@AndroidEntryPoint
class LoginScreen : BaseScreen(R.layout.screen_login), View.OnClickListener {

    override val viewModel: LoginVM by viewModels()
    private val binding by viewBinding(ScreenLoginBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        statusBarColor()
        subscribe()
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

    private fun subscribe() = with(binding) {

//        lifecycleScope.launch {
//            lifecycle.whenStarted {
//                viewModel.loginState.collect {
//                    when (it) {
//                        is ResourceUI.Loading -> {
//                            showSnackBar("Loading")
//                        }
//                        is ResourceUI.Error -> {
//                            showSnackBar(it.error.toString())
//                            binding.progressBar.inVisible()
//                        }
//                        is ResourceUI.Resource -> {
//                            showSnackBar("Success")
//                            findNavController().navigate(R.id.action_loginScreen_to_homeScreen)
//                        }
//                        is ResourceUI.Connect -> {
//                            showSnackBar("Internet bilan aloqa yo'q")
//                        }
//                        else -> {}
//                    }
//                }
//            }
//        }
        viewModel.loginState.map { it ->
            when (it) {
                is ResourceUI.Loading -> {
                    showSnackBar("Loading")
                }
                is ResourceUI.Error -> {
                    showSnackBar(it.error.toString())
                    binding.progressBar.inVisible()
                }
                is ResourceUI.Resource -> {
                    showSnackBar("Success")
                    findNavController().navigate(R.id.action_loginScreen_to_homeScreen)
                }
                is ResourceUI.Connect -> {
                    showSnackBar("Internet bilan aloqa yo'q")
                }
                else -> {}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override
    fun onClick(v: View?) {
        when (v?.id) {
            R.id.signIn -> {
                viewModel.login(
                    binding.editEmail.text.toString(),
                    binding.editPassword.text.toString()
                )
            }
        }
    }

}