package uz.unzosoft.wateruz.data.models.api

import com.google.gson.annotations.SerializedName


/**
 * Created by Abdurashidov Shahzod on 09/03/22 02:06.
 * company QQBank
 * shahzod9933@gmail.com
 */
data class LoginResponse(
    @SerializedName("login") var login: Int? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("token") var token: String? = null,
    @SerializedName("expires_at") var expiresAt: String? = null
)