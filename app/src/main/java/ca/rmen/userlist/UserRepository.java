package ca.rmen.userlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class UserRepository {

    private static class Response {
        List<UserModel> results;
    }

    public interface UserApi {
        @GET("/userlist/data.json")
        Call<Response> listUsers();
    }

    public List<UserModel> fetchUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rmen.ca/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApi userListApi = retrofit.create(UserApi.class);
        Call<Response> call = userListApi.listUsers();

        try {
            return call.execute().body().results;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
