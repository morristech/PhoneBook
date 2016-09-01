package ua.testapp.phonebook.component;

import javax.inject.Singleton;

import dagger.Component;
import ua.testapp.phonebook.loader.ContactsLoader;
import ua.testapp.phonebook.module.ApplicationContextModule;
import ua.testapp.phonebook.module.ContactRepositoryModule;
import ua.testapp.phonebook.module.PhoneRepositoryModule;
import ua.testapp.phonebook.module.UserRepositoryModule;
import ua.testapp.phonebook.module.GsonModule;
import ua.testapp.phonebook.repositories.contact.local.ContactLocalDataSource;
import ua.testapp.phonebook.repositories.contact.remote.ContactRemoteDataSource;
import ua.testapp.phonebook.repositories.phone.local.PhoneLocalDataSource;
import ua.testapp.phonebook.repositories.phone.remote.PhoneRemoteDataSource;
import ua.testapp.phonebook.repositories.user.local.UserLocalDataSource;
import ua.testapp.phonebook.repositories.user.remote.UserRemoteDataSource;
import ua.testapp.phonebook.ui.ContactDetailActivity;
import ua.testapp.phonebook.ui.CreateContactActivity;
import ua.testapp.phonebook.ui.LoginActivity;
import ua.testapp.phonebook.ui.MainActivity;
import ua.testapp.phonebook.ui.SplashActivity;
import ua.testapp.phonebook.ui.adapters.ContactAdapter;

/**
 * Created by alexey on 26.08.16.
 */
@Singleton
@Component(modules = {
        ApplicationContextModule.class,
        GsonModule.class,
        UserRepositoryModule.class,
        ContactRepositoryModule.class,
        PhoneRepositoryModule.class})
public interface GeneralComponent {
    // activity
    void inject(SplashActivity splashActivity);
    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);
    void inject(CreateContactActivity createContactActivity);
    void inject(ContactDetailActivity contactDetailActivity);

    //repository
    void inject(UserLocalDataSource credentialLocalDataSource);
    void inject(UserRemoteDataSource credentialRemoteDataSources);
    void inject(ContactLocalDataSource contactLocalDataSource);
    void inject(ContactRemoteDataSource contactRemoteDataSource);
    void inject(PhoneLocalDataSource phoneLocalDataSource);
    void inject(PhoneRemoteDataSource phoneRemoteDataSource);

    //loader
    void inject(ContactsLoader contactsLoader);

    //adapter
    void inject(ContactAdapter contactAdapter);
}
