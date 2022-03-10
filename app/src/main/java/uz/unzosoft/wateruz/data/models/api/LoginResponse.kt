package uz.unzosoft.wateruz.data.models.api

import com.google.gson.annotations.SerializedName


/**
 * Created by Abdurashidov Shahzod on 09/03/22 02:06.
 * company QQBank
 * shahzod9933@gmail.com
 */
data class LoginResponse(
    @SerializedName("access_token") var accessToken: String? = null,
    @SerializedName("token_type") var tokenType: String? = null,
    @SerializedName("expires_in") var expiresIn: Int? = null,
    @SerializedName("user") var user: User? = User()
)

data class User(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("email_verified_at") var emailVerifiedAt: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null

)