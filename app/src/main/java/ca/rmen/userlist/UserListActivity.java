package ca.rmen.userlist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import ca.rmen.userlist.databinding.MainBinding;


public class UserListActivity extends ActionBarActivity {

    private static final String TAG = UserListActivity.class.getSimpleName();
    private UserListViewModel mViewModel = new UserListViewModel();
    private MainBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = MainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.setViewModel(mViewModel);

        mBinding.recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.recyclerView.setLayoutManager(layoutManager);
        mViewModel.users.addOnPropertyChangedCallback(mUserListListener);
        mViewModel.refresh();
    }

    @Override
    protected void onDestroy() {
        mViewModel.users.removeOnPropertyChangedCallback(mUserListListener);
        super.onDestroy();
    }

    private Observable.OnPropertyChangedCallback mUserListListener = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            mBinding.recyclerView.setAdapter(new UserListAdapter(((ObservableField<List<UserUiModel>>) sender).get()));
        }
    };

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
