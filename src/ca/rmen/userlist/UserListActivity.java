package ca.rmen.userlist;

import java.util.List;

import ca.rmen.userlist.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class UserListActivity extends Activity {

	private static final String TAG = UserListActivity.class.getSimpleName();
	private UserRepository mRepository = new UserRepository();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ListView listView = (ListView) findViewById(R.id.listView1);

		new AsyncTask<Void, Void, List<UserModel>>() {

			@Override
			protected List<UserModel> doInBackground(Void... params) {
				return mRepository.fetchUsers();
			}

			@Override
			protected void onPostExecute(List<UserModel> users) {
				Log.v(TAG, "Got user list");
				listView.setAdapter(new UserListAdapter(UserListActivity.this, users));
			}
		}.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

}
