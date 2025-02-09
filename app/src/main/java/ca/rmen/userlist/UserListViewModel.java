package ca.rmen.userlist;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;

import java.util.List;


public class UserListViewModel extends AndroidViewModel {
    private UserRepository mRepository = new UserRepository();

    private MutableLiveData<Boolean> mIsError = new MutableLiveData<>();
    public LiveData<Boolean> isError = mIsError;

    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = mIsLoading;

    private MutableLiveData<List<UserUiModel>> mUsers = new MutableLiveData<>();
    LiveData<List<UserUiModel>> users = mUsers;

    public UserListViewModel(@NonNull Application application) {
        super(application);
        mIsError.setValue(false);
        mIsLoading.setValue(false);
    }

    public void refresh() {
        mIsLoading.setValue(true);
        mRepository.fetchUsers()
                .toObservable()
                .flatMapIterable(apiUsers -> apiUsers)
                .map(
                        apiUser -> new UserUiModel(
                                apiUser.name.first + " " + apiUser.name.last,
                                apiUser.picture.thumbnail
                        )
                ).toList()
                .subscribe(userModels -> {
                            mUsers.setValue(userModels);
                            mIsError.setValue(false);
                            mIsLoading.setValue(false);
                        },
                        throwable -> {
                            mIsError.setValue(true);
                            mIsLoading.setValue(false);
                        }
                );
    }
}
