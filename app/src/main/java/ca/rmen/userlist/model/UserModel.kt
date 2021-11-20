package ca.rmen.userlist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    @SerializedName("name")
    val name: UserNameModel = UserNameModel(),

    @SerializedName("id")
    val id: UserIdModel = UserIdModel(),

    @SerializedName("picture")
    val picture: UserPictureModel = UserPictureModel(),

    @SerializedName("phone")
    val phone: String? = null
) : Parcelable
