package ca.rmen.userlist;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class UserRepository {
	private final String sourceUrl = "http://192.168.1.141:8080/data.json";
	private static final TypeToken<Response> RESPONSE = new TypeToken<Response>() {
	};
	private static final Gson GSON = new Gson();

	private static class Response {
		List<UserModel> results;
	}

	public List<UserModel> fetchUsers() {
		try {

			OkHttpClient client = new OkHttpClient();
			HttpURLConnection connection = client.open(new URL(sourceUrl));
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			Response resp = GSON.fromJson(isr, RESPONSE.getType());
			return resp.results;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static Bitmap getAvatar(UserModel user) {
		try {
			OkHttpClient client = new OkHttpClient();
			HttpURLConnection connection = client.open(new URL(user.picture.thumbnail));
			InputStream is = connection.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
