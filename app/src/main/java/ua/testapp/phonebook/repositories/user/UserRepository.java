package ua.testapp.phonebook.repositories.user;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import ua.testapp.phonebook.model.User;
import ua.testapp.phonebook.repositories.Local;
import ua.testapp.phonebook.repositories.Remote;

/**
 * Created by alexey on 28.08.16.
 */
public class UserRepository implements UserDataSource {
    private static final String TAG = UserRepository.class.getSimpleName();

    private final UserDataSource mUserLocalDataSource;
    private final UserDataSource mUserRemoteDataSource;

    @Inject
    UserRepository(@Remote UserDataSource userRemoteDataSource,
                   @Local UserDataSource UserLocalDataSource) {
        mUserLocalDataSource = UserLocalDataSource;

        /** use when need call api */
        mUserRemoteDataSource = userRemoteDataSource;
    }


    @Override
    public void signIn(@NonNull User user, @NonNull ActionUserCallback callback) {
        mUserLocalDataSource.signIn(user, new ActionUserCallback() {
            @Override
            public void onSuccessAction() {
                callback.onSuccessAction();
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void signUp(@NonNull User user, @NonNull ActionUserCallback callback) {
        mUserLocalDataSource.signUp(user, new ActionUserCallback() {
            @Override
            public void onSuccessAction() {
                callback.onSuccessAction();
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void updateUser(@NonNull User user, @NonNull ActionUserCallback callback) {
        mUserLocalDataSource.updateUser(user, new ActionUserCallback() {
            @Override
            public void onSuccessAction() {
                callback.onSuccessAction();
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void deleteUser(@NonNull User user, @NonNull ActionUserCallback callback) {
        mUserLocalDataSource.deleteUser(user, new ActionUserCallback() {
            @Override
            public void onSuccessAction() {
                callback.onSuccessAction();
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
