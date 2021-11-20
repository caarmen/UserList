package ca.rmen.userlist.viewmodel

import ca.rmen.userlist.model.UserModel

object ApiMapping {
    fun convert(userModel: UserModel): UserDisplayData =
        UserDisplayData(
            id = userModel.id.value ?: "",
            name = "${userModel.name.first} ${userModel.name.last}",
            smallAvatarUrl = userModel.picture.thumbnail,
            largeAvatarUrl = userModel.picture.large,
            phone = userModel.phone
        )
}
