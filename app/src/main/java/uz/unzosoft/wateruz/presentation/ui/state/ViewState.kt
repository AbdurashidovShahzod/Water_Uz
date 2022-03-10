package uz.unzosoft.wateruz.presentation.ui.state


/**
 * Created by Abdurashidov Shahzod on 10/03/22 01:25.
 * company QQBank
 * shahzod9933@gmail.com
 */

sealed class ViewState

data class Success<T>(val data: T?) : ViewState()
object Initial : ViewState()
object Loading : ViewState()
object Connect : ViewState()
data class ServerError(val errorMessage: String, val code: Int) : ViewState()
data class Fail(val exception: Exception) : ViewState()