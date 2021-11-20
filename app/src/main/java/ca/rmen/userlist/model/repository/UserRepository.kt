package ca.rmen.userlist.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ca.rmen.userlist.model.api.UserModel
import kotlinx.coroutines.flow.Flow


class UserRepository {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    fun getUsersStream(): Flow<PagingData<UserModel>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { UserPagingSource() }
    ).flow
}