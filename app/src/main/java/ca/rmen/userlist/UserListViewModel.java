package ca.rmen.userlist;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserListViewModel {
    private UserRepository mRepository = new UserRepository();

    public ObservableBoolean isLoading = new ObservableBoolean(false);
    ObservableField<List<UserUiModel>> users = new ObservableField<>();

    public void refresh() {
        isLoading.set(true);
        mRepository.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(apiUsers -> apiUsers)
                .map(
                        apiUser -> new UserUiModel(
                                apiUser.name.first + " " + apiUser.name.last,
                                apiUser.picture.thumbnail
                        )
                ).toList()
                .subscribe(userModels -> {
                            users.set(userModels);
                            isLoading.set(false);
                        }
                );
    }
}
