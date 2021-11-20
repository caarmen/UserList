package ca.rmen.userlist.model

import com.google.gson.annotations.SerializedName

data class UserNameModel(
    @SerializedName("first")
    val first: String? = null,
    @SerializedName("last")
    val last: String? = null
)
