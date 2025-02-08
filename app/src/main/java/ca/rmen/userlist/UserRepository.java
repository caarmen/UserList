package ca.rmen.userlist;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class UserRepository {

    private final UserApi mUserApi;

    public UserRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rmen.ca/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mUserApi = retrofit.create(UserApi.class);
    }

    private static class Response {
        List<UserApiModel> results;
    }

    public interface UserApi {
        @GET("/userlist/data.json")
        Single<Response> listUsers();
    }

    public Single<List<UserApiModel>> fetchUsers() {
        return mUserApi.listUsers().map(response -> response.results)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
