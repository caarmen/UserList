package ca.rmen.userlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private static final String TAG = UserListAdapter.class.getSimpleName();

    private final List<UserModel> mUsers;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mAvatarView;
        public TextView mNameView;

        public ViewHolder(View parent) {
            super(parent);
            mAvatarView = (ImageView) parent.findViewById(R.id.avatar);
            mNameView = (TextView) parent.findViewById(R.id.name);
        }
    }

    public UserListAdapter(List<UserModel> users) {
        mUsers = users;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final UserModel user = mUsers.get(position);
        holder.mNameView.setText(user.name.first + " " + user.name.last);
        Glide.with(holder.mAvatarView.getContext()).load(user.picture.thumbnail).into(holder.mAvatarView);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}
