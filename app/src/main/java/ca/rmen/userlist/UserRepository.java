package ca.rmen.userlist;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public class UserRepository {

    private static class Response {
        List<UserApiModel> results;
    }

    public interface UserApi {
        @GET("/userlist/data.json")
        Observable<Response> listUsers();
    }

    public Observable<List<UserUiModel>> fetchUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rmen.ca/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        UserApi userListApi = retrofit.create(UserApi.class);
        return userListApi.listUsers().map(response -> response.results)
                .flatMapIterable(apiUsers -> apiUsers).map(
                        apiUser -> new UserUiModel(apiUser.name.first + " " + apiUser.name.last, apiUser.picture.thumbnail)
                ).toList();
    }
}
