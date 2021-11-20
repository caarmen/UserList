package ca.rmen.userlist.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class UserRepository {
    interface UserListService {
        @GET("api/?results=50&seed=abc")
        fun listRepos(): Call<UserListModel>
    }

    private val service: UserListService by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserListService::class.java)
    }

    fun getUsers(callback: (UserListModel?) -> Unit) {
        val retrofitCallback: Callback<UserListModel> = object : Callback<UserListModel> {
            override fun onResponse(
                call: Call<UserListModel>,
                response: Response<UserListModel>
            ) {
                callback(response.body())
            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                callback(null)
            }
        }
        service.listRepos().enqueue(retrofitCallback)
    }
}