package ca.rmen.userlist;

import android.app.Dialog;
import androidx.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import ca.rmen.userlist.databinding.MainBinding;


public class UserListActivity extends AppCompatActivity {

    private static final String TAG = UserListActivity.class.getSimpleName();
    private UserListViewModel mViewModel;
    private MainBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = MainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.recyclerView.setLayoutManager(layoutManager);

        mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(UserListViewModel.class);
        mBinding.setViewModel(mViewModel);
        mViewModel.users.observe(this, users -> {
            mBinding.recyclerView.setAdapter(new UserListAdapter(users));
        });
        mViewModel.refresh();
        mBinding.setLifecycleOwner(this);
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

    public static class AboutDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getContext()).setTitle(R.string.app_name)
                    .setMessage(R.string.about_copyright)
                    .setPositiveButton(android.R.string.ok, new EmptyDialogListener()).create();
        }

        public static final String TAG = "AboutDialogFragment";
    }

    public static class LicenseDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getContext()).setTitle(R.string.menu_item_licenses)
                    .setItems(R.array.licenses, null).setPositiveButton(android.R.string.ok, new EmptyDialogListener())
                    .create();
        }

        public static final String TAG = "LicenseDialogFragment";

    }

    static class EmptyDialogListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface arg0, int arg1) {
        }
    }

}
