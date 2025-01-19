package ca.rmen.userlist;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserRepository {

    private final UserApi mUserApi;

    public UserRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rmen.ca/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
