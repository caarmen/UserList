package ca.rmen.userlist.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlin.coroutines.CoroutineContext


class UserRepository {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    fun getUsersStream(context: CoroutineContext): LiveData<PagingData<UserModel>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { UserPagingSource() }
    ).flow.asLiveData(context, 100L)
}