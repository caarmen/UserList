package ca.rmen.userlist;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ca.rmen.userlist.databinding.UserItemBinding;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private static final String TAG = UserListAdapter.class.getSimpleName();

    private final List<UserUiModel> mUsers;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final UserItemBinding binding;

        public ViewHolder(UserItemBinding userItemBinding) {
            super(userItemBinding.getRoot());
            this.binding = userItemBinding;
        }
    }

    public UserListAdapter(List<UserUiModel> users) {
        mUsers = users;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserItemBinding userItemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(userItemBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.binding.setUser(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}
