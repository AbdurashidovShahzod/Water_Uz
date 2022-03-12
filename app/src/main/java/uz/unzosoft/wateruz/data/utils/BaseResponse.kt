package uz.unzosoft.wateruz.data.utils

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data") var data: T?,
    @SerializedName("error_message") var errorMessage: String?
)