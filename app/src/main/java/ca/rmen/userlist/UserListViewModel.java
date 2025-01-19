package ca.rmen.userlist;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;


public class UserListViewModel {
    private UserRepository mRepository = new UserRepository();

    public ObservableBoolean isError = new ObservableBoolean(false);
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    ObservableField<List<UserUiModel>> users = new ObservableField<>();

    public void refresh() {
        isLoading.set(true);
        mRepository.fetchUsers()
                .flatMapIterable(apiUsers -> apiUsers)
                .map(
                        apiUser -> new UserUiModel(
                                apiUser.name.first + " " + apiUser.name.last,
                                apiUser.picture.thumbnail
                        )
                ).toList()
                .subscribe(userModels -> {
                            users.set(userModels);
                            isError.set(false);
                            isLoading.set(false);
                        },
                        throwable -> {
                            isError.set(true);
                            isLoading.set(false);
                        }
                );
    }
}
