package ua.testapp.phonebook.repositories.user.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.model.User;
import ua.testapp.phonebook.repositories.user.UserDataSource;

public class UserRemoteDataSource implements UserDataSource {
    private static final String TAG = UserRemoteDataSource.class.getSimpleName();

    public UserRemoteDataSource(@NonNull Context context) {
        ((PhoneBookApplication) context).getGeneralComponent().inject(this);
    }


    @Override
    public void signIn(@NonNull User user, @NonNull ActionUserCallback callback) {
        //call server
    }

    @Override
    public void signUp(@NonNull User user, @NonNull ActionUserCallback callback) {
        //call server
    }

    @Override
    public void updateUser(@NonNull User user, @NonNull ActionUserCallback callback) {
        //call server
    }

    @Override
    public void deleteUser(@NonNull User user, @NonNull ActionUserCallback callback) {
        //call server
    }
}
