package ca.rmen.userlist.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class UserPagingSource : PagingSource<Int, UserModel>() {
    interface UserListService {
        @GET("api/")
        suspend fun listRepos(
            @Query("page") page: Int,
            @Query("results") results: Int = 50,
            @Query("seed") seed: String = "abc"
        ): UserListModel
    }

    private val service: UserListService by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserListService::class.java)
    }

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        val pageNumber = params.key ?: 0
        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        return try {
            val users = service.listRepos(pageNumber)
            val nextKey = if (users.users.isNotEmpty()) pageNumber + 1 else null
            LoadResult.Page(
                data = users.users,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (t: Throwable) {
            LoadResult.Error(t)
        }
    }
}