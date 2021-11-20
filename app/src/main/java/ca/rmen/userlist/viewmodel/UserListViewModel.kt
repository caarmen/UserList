package ca.rmen.userlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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

    val users = repository.getUsersStream(viewModelScope.coroutineContext)
}