package ca.rmen.userlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

    val horse = MutableLiveData("initial")

    init {
        repository.getUsers {
            horse.value = it.users.firstOrNull()?.name?.first ?: "nothing found"
        }
    }
}