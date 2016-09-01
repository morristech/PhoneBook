package ua.testapp.phonebook.repositories.contact.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.model.Contact;
import ua.testapp.phonebook.repositories.contact.ContactCriteria;
import ua.testapp.phonebook.repositories.contact.ContactDataSource;

public class ContactRemoteDataSource implements ContactDataSource {
    private static final String TAG = ContactRemoteDataSource.class.getSimpleName();

    public ContactRemoteDataSource(@NonNull Context context) {
        ((PhoneBookApplication) context).getGeneralComponent().inject(this);
    }

    @Override
    public List<Contact> getContactList(ContactCriteria contactCriteria) {
        //call server
        return null;
    }

    @Override
    public void getContact(@NonNull String contactId, @NonNull ActionContactCallback callback) {
        //call server
    }

    @Override
    public void saveContact(@NonNull Contact contact, @NonNull ActionContactCallback callback) {
        //call server
    }

    @Override
    public boolean saveAllContacts(@NonNull List<Contact> phoneList) {
        //call server
        return true;
    }

    @Override
    public void updateContact(@NonNull Contact contact, @NonNull ActionContactCallback callback) {
        //call server
    }

    @Override
    public boolean deleteAllContacts() {
        //call server
        return true;
    }

    @Override
    public void deleteContact(@NonNull Contact contact, @NonNull ActionContactCallback callback) {
        //call server
    }
}
