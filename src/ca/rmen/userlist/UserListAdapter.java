package ca.rmen.userlist;

import java.util.List;

import ca.rmen.userlist.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserListAdapter extends ArrayAdapter<UserModel> {

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
	public View getView(int position, View convertView, ViewGroup parent) {
		UserModel user = mUsers.get(position);
		View result = convertView;
		if (result == null) {
			result = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, null);
		}
		TextView nameView = (TextView) result.findViewById(R.id.name);
		ImageView avatarView = (ImageView) result.findViewById(R.id.avatar);
		nameView.setText(user.name.first + " " + user.name.last);
		avatarView.setImageBitmap(UserRepository.getAvatar(user));
		return result;
	}

}
