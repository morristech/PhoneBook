package ua.testapp.phonebook.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.testapp.phonebook.repositories.Local;
import ua.testapp.phonebook.repositories.Remote;
import ua.testapp.phonebook.repositories.contact.ContactDataSource;
import ua.testapp.phonebook.repositories.contact.local.ContactLocalDataSource;
import ua.testapp.phonebook.repositories.contact.remote.ContactRemoteDataSource;

@Module
public class ContactRepositoryModule {

    @Singleton
    @Provides
    @Local
    ContactDataSource provideContactLocalDataSource(Context context) {
        return new ContactLocalDataSource(context);
    }

    @Singleton
    @Provides
    @Remote
    ContactDataSource provideContactRemoteDataSource(Context context) {
        return new ContactRemoteDataSource(context);
    }

}
