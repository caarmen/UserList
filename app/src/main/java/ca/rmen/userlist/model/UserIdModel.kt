package ca.rmen.userlist.model

import com.google.gson.annotations.SerializedName

data class UserIdModel(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("value")
    val value: String? = null
)
