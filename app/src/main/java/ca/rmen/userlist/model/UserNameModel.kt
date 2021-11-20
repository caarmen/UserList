package ca.rmen.userlist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserNameModel(
    @SerializedName("first")
    val first: String? = null,
    @SerializedName("last")
    val last: String? = null
) : Parcelable
