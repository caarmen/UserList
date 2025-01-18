package ca.rmen.userlist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserListActivity extends ActionBarActivity {

    private static final String TAG = UserListActivity.class.getSimpleName();
    private UserRepository mRepository = new UserRepository();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mRepository.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userModels ->
                        recyclerView.setAdapter(new UserListAdapter(userModels))
                );
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

    public class AboutDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(UserListActivity.this).setTitle(R.string.app_name)
                    .setMessage(R.string.about_copyright)
                    .setPositiveButton(android.R.string.ok, new EmptyDialogListener()).create();
        }

        public static final String TAG = "AboutDialogFragment";
    }

    public class LicenseDialogFragment extends DialogFragment {
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
