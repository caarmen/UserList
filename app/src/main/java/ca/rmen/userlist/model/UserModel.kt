package ca.rmen.userlist.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("name")
    val name: UserNameModel = UserNameModel()
)
