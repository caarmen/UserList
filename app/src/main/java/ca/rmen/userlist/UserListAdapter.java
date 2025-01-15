package ca.rmen.userlist;

import java.util.List;

import ca.rmen.userlist.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserListAdapter extends ArrayAdapter<UserModel> {
	private static final String TAG = UserListAdapter.class.getSimpleName();

	private final List<UserModel> mUsers;

	public UserListAdapter(Context context, List<UserModel> users) {
		super(context, R.layout.user_item, users);
		mUsers = users;
	}

	@Override
	public long getItemId(int position) {
		return -1;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final UserModel user = mUsers.get(position);
		// Get or create our user item view.
		final View result = convertView == null
				? LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, null)
				: convertView;

		// Set the name.
		TextView nameView = (TextView) result.findViewById(R.id.name);
		final ImageView avatarView = (ImageView) result.findViewById(R.id.avatar);
		nameView.setText(user.name.first + " " + user.name.last);

		// Set the image. Fetch the image in the background.
		AsyncTask<UserModel, Void, Bitmap> previousTask = (AsyncTask<UserModel, Void, Bitmap>) result.getTag();
		if (previousTask != null)
			previousTask.cancel(true);
		AsyncTask<UserModel, Void, Bitmap> avatarTask = new AsyncTask<UserModel, Void, Bitmap>() {

			@Override
			protected Bitmap doInBackground(UserModel... user) {
				Log.d(TAG, "doInBackground, position = " + position + ", user = " + user[0].name.first);
				return UserRepository.getAvatar(user[0]);
			}

			@Override
			protected void onPostExecute(Bitmap bitmap) {
				Log.d(TAG, "onPostExecute, isShown = " + result.isShown() + ", position = " + position + ", user = "
						+ user.name.first);
				// If the user is scrolling quickly, this view may not be for
				// this avatar anymore.
				// Libraries like Picasso and Glide handle this nicely for us,
				// but they require more recent versions of Android.
				if (!isCancelled()) {
					avatarView.setImageBitmap(bitmap);
				}
			}

		};
		result.setTag(avatarTask);
		avatarTask.execute(user);
		return result;
	}

}
