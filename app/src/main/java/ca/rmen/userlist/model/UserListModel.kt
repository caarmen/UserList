package ca.rmen.userlist.model

import com.google.gson.annotations.SerializedName

data class UserListModel(
    @SerializedName("results")
    val users: List<UserModel> = emptyList()
)
