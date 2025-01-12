package ca.rmen.userlist;

import java.util.List;

import ca.rmen.userlist.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class UserListActivity extends Activity {

	private UserRepository mRepository = new UserRepository();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView listView = (ListView) findViewById(R.id.listView1);
		List<UserModel> users = mRepository.fetchUsers();
		listView.setAdapter(new UserListAdapter(UserListActivity.this, users));
	}

}
