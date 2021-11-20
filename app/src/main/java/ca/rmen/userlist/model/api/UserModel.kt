package ca.rmen.userlist.model.api

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("name")
    val name: UserNameModel = UserNameModel(),

    @SerializedName("id")
    val id: UserIdModel = UserIdModel(),

    @SerializedName("picture")
    val picture: UserPictureModel = UserPictureModel(),

    @SerializedName("phone")
    val phone: String? = null
)
