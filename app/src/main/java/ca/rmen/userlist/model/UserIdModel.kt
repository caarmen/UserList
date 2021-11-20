package ca.rmen.userlist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserIdModel(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("value")
    val value: String? = null
) : Parcelable
