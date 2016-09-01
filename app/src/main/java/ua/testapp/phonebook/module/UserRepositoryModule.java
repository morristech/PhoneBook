package ua.testapp.phonebook.module;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import ua.testapp.phonebook.repositories.Local;
import ua.testapp.phonebook.repositories.Remote;
import ua.testapp.phonebook.repositories.user.UserDataSource;
import ua.testapp.phonebook.repositories.user.local.UserLocalDataSource;
import ua.testapp.phonebook.repositories.user.remote.UserRemoteDataSource;

@Module
public class UserRepositoryModule {

    @Singleton
    @Provides
    @Local
    UserDataSource provideUserLocalDataSource(Context context) {
        return new UserLocalDataSource(context);
    }

    @Singleton
    @Provides
    @Remote
    UserDataSource provideUserRemoteDataSource(Context context) {
        return new UserRemoteDataSource(context);
    }

}
