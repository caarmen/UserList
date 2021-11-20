package ca.rmen.userlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.rmen.userlist.model.UserListModel
import ca.rmen.userlist.model.UserRepository

class UserListViewModel(repository: UserRepository = UserRepository()) : ViewModel() {
    companion object {
        fun factory(repository: UserRepository = UserRepository()): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    UserListViewModel(repository) as T
            }
        }
    }

    val users = MutableLiveData<UserListModel>()

    val isErrorBannerVisible = MutableLiveData(false)

    init {
        repository.getUsers { userListModel ->
            if (userListModel != null) {
                users.value = userListModel
            }
            isErrorBannerVisible.value = userListModel == null
        }
    }
}