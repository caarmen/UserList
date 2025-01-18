package ca.rmen.userlist;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.functions.Func1;

public class UserRepository {

    private static class Response {
        List<UserModel> results;
    }

    public interface UserApi {
        @GET("/userlist/data.json")
        Observable<Response> listUsers();
    }

    public Observable<List<UserModel>> fetchUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rmen.ca/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        UserApi userListApi = retrofit.create(UserApi.class);
        return userListApi.listUsers().map(new Func1<Response, List<UserModel>>() {
            @Override
            public List<UserModel> call(Response response) {
                return response.results;
            }
        });

    }
}
