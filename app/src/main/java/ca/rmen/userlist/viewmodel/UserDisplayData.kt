package ca.rmen.userlist.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDisplayData(
    val id: String,
    val name: String,
    val smallAvatarUrl: String?,
    val largeAvatarUrl: String?,
    val phone: String?
) : Parcelable
