package ua.testapp.phonebook.repositories.user;

import android.support.annotation.NonNull;
import ua.testapp.phonebook.model.User;

public interface UserDataSource {

    interface ActionUserCallback {

        void onSuccessAction();

        void onDataNotAvailable();
    }

    void signIn(@NonNull User user, @NonNull ActionUserCallback callback);

    void signUp(@NonNull User user, @NonNull ActionUserCallback callback);

    void updateUser(@NonNull User user, @NonNull ActionUserCallback callback);

    void deleteUser(@NonNull User user, @NonNull ActionUserCallback callback);
}
