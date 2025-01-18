package ca.rmen.userlist;

import java.util.List;

import com.bumptech.glide.Glide;

import android.content.Context;
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
		Glide.load(user.picture.thumbnail).into(avatarView);
		return result;
	}

}
