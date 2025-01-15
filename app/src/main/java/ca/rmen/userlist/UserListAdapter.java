package ca.rmen.userlist;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private static final String TAG = UserListAdapter.class.getSimpleName();

    private final List<UserModel> mUsers;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mAvatarView;
        public TextView mNameView;
        public AsyncTask<UserModel, Void, Bitmap> mImageLoadingTask = null;

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
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final UserModel user = mUsers.get(position);

        holder.mNameView.setText(user.name.first + " " + user.name.last);

        // Set the image. Fetch the image in the background.
        if (holder.mImageLoadingTask != null)
            holder.mImageLoadingTask.cancel(true);
        holder.mImageLoadingTask = new AsyncTask<UserModel, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(UserModel... user) {
                Log.d(TAG, "doInBackground, position = " + position + ", user = " + user[0].name.first);
                return UserRepository.getAvatar(user[0]);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                Log.d(TAG, "onPostExecute, isShown = " + holder.itemView.isShown() + ", position = " + position + ", user = "
                        + user.name.first);
                // If the user is scrolling quickly, this view may not be for
                // this avatar anymore.
                // Libraries like Picasso and Glide handle this nicely for us,
                // but they require more recent versions of Android.
                if (!isCancelled()) {
                    holder.mAvatarView.setImageBitmap(bitmap);
                }
            }

        };
        holder.mImageLoadingTask.execute(user);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}
