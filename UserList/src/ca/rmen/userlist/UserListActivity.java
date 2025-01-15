package ca.rmen.userlist;

import java.util.List;

import ca.rmen.userlist.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.widget.ListView;
import android.support.v4.app.ActionBar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

public class UserListActivity extends FragmentActivity {

	private static final String TAG = UserListActivity.class.getSimpleName();
	private UserRepository mRepository = new UserRepository();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(false);
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_item_about) {
			new AboutDialogFragment().show(getSupportFragmentManager(), AboutDialogFragment.TAG);
		} else if (item.getItemId() == R.id.menu_item_licenses) {
			new LicenseDialogFragment().show(getSupportFragmentManager(), LicenseDialogFragment.TAG);
		}
		return false;
	}

	class AboutDialogFragment extends DialogFragment {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new AlertDialog.Builder(UserListActivity.this).setTitle(R.string.app_name)
					.setMessage(R.string.about_copyright)
					.setPositiveButton(android.R.string.ok, new EmptyDialogListener()).create();
		}

		public static final String TAG = "AboutDialogFragment";
	}

	class LicenseDialogFragment extends DialogFragment {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new AlertDialog.Builder(UserListActivity.this).setTitle(R.string.menu_item_licenses)
					.setItems(R.array.licenses, null).setPositiveButton(android.R.string.ok, new EmptyDialogListener())
					.create();
		}

		public static final String TAG = "LicenseDialogFragment";

	}

	class EmptyDialogListener implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
		}
	}

}
