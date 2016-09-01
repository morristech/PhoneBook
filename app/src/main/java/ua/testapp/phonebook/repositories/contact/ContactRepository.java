package ua.testapp.phonebook.repositories.contact;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import ua.testapp.phonebook.model.Contact;
import ua.testapp.phonebook.repositories.Local;
import ua.testapp.phonebook.repositories.Remote;
import ua.testapp.phonebook.utils.ExternalStorageUtil;

/**
 * Created by alexey on 28.08.16.
 */
public class ContactRepository implements ContactDataSource {
    private static final String TAG = ContactRepository.class.getSimpleName();

    private final ContactDataSource mContactLocalDataSource;
    private final ContactDataSource mContactRemoteDataSource;

    @Inject
    ExternalStorageUtil mExternalStorageUtil;

    @Inject
    ContactRepository(@Remote ContactDataSource contactRemoteDataSource,
                      @Local ContactDataSource contactLocalDataSource) {
        mContactLocalDataSource = contactLocalDataSource;

        /** use when need call api */
        mContactRemoteDataSource = contactRemoteDataSource;
    }

    @Override
    public List<Contact> getContactList(ContactCriteria contactCriteria) {
        return mContactLocalDataSource.getContactList(contactCriteria);
    }

    @Override
    public void getContact(@NonNull String contactId, @NonNull ActionContactCallback callback) {
        mContactLocalDataSource.getContact(contactId, new ActionContactCallback() {
            @Override
            public void onSuccessAction(Contact contact) {
                callback.onSuccessAction(contact);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void saveContact(@NonNull Contact contact, @NonNull ActionContactCallback callback) {
        mContactLocalDataSource.saveContact(contact, new ActionContactCallback() {
            @Override
            public void onSuccessAction(Contact contact) {
                callback.onSuccessAction(contact);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public boolean saveAllContacts(@NonNull List<Contact> phoneList) {
        /** sync contacts with server */
        return mContactRemoteDataSource.saveAllContacts(phoneList);
    }

    @Override
    public void updateContact(@NonNull Contact contact, @NonNull ActionContactCallback callback) {
        mContactLocalDataSource.updateContact(contact, new ActionContactCallback() {
            @Override
            public void onSuccessAction(Contact contact) {
                callback.onSuccessAction(contact);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public boolean deleteAllContacts() {
        /** delete contacts with server */
        return mContactRemoteDataSource.deleteAllContacts();
    }

    @Override
    public void deleteContact(@NonNull Contact contact, @NonNull ActionContactCallback callback) {
        mContactLocalDataSource.deleteContact(contact, new ActionContactCallback() {
            @Override
            public void onSuccessAction(Contact contact) {
                // delete in ext store
                callback.onSuccessAction(contact);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
