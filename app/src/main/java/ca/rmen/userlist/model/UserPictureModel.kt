package ca.rmen.userlist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPictureModel(
    @SerializedName("thumbnail")
    val thumbnail: String? = null,

    @SerializedName("large")
    val large: String? = null
) : Parcelable
