package ua.testapp.phonebook.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.testapp.phonebook.repositories.Local;
import ua.testapp.phonebook.repositories.Remote;
import ua.testapp.phonebook.repositories.phone.PhoneDataSource;
import ua.testapp.phonebook.repositories.phone.local.PhoneLocalDataSource;
import ua.testapp.phonebook.repositories.phone.remote.PhoneRemoteDataSource;

@Module
public class PhoneRepositoryModule {

    @Singleton
    @Provides
    @Local
    PhoneDataSource providePhoneLocalDataSource(Context context) {
        return new PhoneLocalDataSource(context);
    }

    @Singleton
    @Provides
    @Remote
    PhoneDataSource providePhoneRemoteDataSource(Context context) {
        return new PhoneRemoteDataSource(context);
    }

}
