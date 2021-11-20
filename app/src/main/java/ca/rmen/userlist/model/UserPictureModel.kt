package ca.rmen.userlist.model

import com.google.gson.annotations.SerializedName

data class UserPictureModel(
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
)
