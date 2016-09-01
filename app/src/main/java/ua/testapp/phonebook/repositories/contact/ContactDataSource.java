package ua.testapp.phonebook.repositories.contact;

import android.support.annotation.NonNull;
import java.util.List;
import ua.testapp.phonebook.model.Contact;

public interface ContactDataSource {

    interface ActionContactCallback {

        void onSuccessAction(Contact contact);

        void onDataNotAvailable();
    }

    List<Contact> getContactList(ContactCriteria contactCriteria);

    void getContact(@NonNull String contactId, @NonNull ActionContactCallback callback);

    void saveContact(@NonNull Contact contact, @NonNull ActionContactCallback callback);

    boolean saveAllContacts(@NonNull List<Contact> phoneList);

    void updateContact(@NonNull Contact contact, @NonNull ActionContactCallback callback);

    boolean deleteAllContacts();

    void deleteContact(@NonNull Contact contact, @NonNull ActionContactCallback callback);
}
