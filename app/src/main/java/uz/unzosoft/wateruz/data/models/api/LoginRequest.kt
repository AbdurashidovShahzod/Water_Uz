package uz.unzosoft.wateruz.data.models.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field


/**
 * Created by Abdurashidov Shahzod on 09/03/22 02:03.
 * company QQBank
 * shahzod9933@gmail.com
 */

data class LoginRequest(
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null
)
