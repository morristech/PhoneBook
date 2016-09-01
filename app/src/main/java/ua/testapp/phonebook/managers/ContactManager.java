package ua.testapp.phonebook.managers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ua.testapp.phonebook.interfaces.TaskCompleteListener;
import ua.testapp.phonebook.model.Contact;
import ua.testapp.phonebook.model.Phone;
import ua.testapp.phonebook.repositories.contact.ContactCriteria;
import ua.testapp.phonebook.repositories.contact.ContactDataSource;
import ua.testapp.phonebook.repositories.contact.ContactRepository;
import ua.testapp.phonebook.repositories.phone.PhoneCriteria;
import ua.testapp.phonebook.repositories.phone.PhoneDataSource;
import ua.testapp.phonebook.repositories.phone.PhoneRepository;

/**
 * Created by alexey on 30.08.16.
 */
public class ContactManager {

    private List<Contact> contactList;

    @Inject
    ContactRepository mContactRepository;

    @Inject
    PhoneRepository mPhoneRepository;

    @Inject
    public ContactManager() {
    }

    public List<Contact> loadContacts(ContactCriteria contactCriteria){
        contactList = new ArrayList<>();
        contactList.addAll(mContactRepository.getContactList(contactCriteria));

        for (Contact contact : contactList) {
            contact.setPhoneList(mPhoneRepository.getPhoneList(new PhoneCriteria(contact.getId())));
        }

        return contactList;
    }

    public void createContact(Contact newContact, TaskCompleteListener taskCompleteListener){
        mContactRepository.saveContact(newContact, new ContactDataSource.ActionContactCallback() {
            @Override
            public void onSuccessAction(Contact contact) {
                mPhoneRepository.saveAllPhones(newContact.getPhoneList());
                taskCompleteListener.onTaskComplete(true);
            }

            @Override
            public void onDataNotAvailable() {
                taskCompleteListener.onTaskComplete(false);
            }
        });
    }


    public void removeContacts(List<Contact> contactList, TaskCompleteListener taskCompleteListener) {
        for (Contact contactTemp : contactList) {
            mContactRepository.deleteContact(contactTemp, new ContactDataSource.ActionContactCallback() {
                @Override
                public void onSuccessAction(Contact contact) {
                    mPhoneRepository.deletePhone(contactTemp.getPhoneList().get(0), new PhoneDataSource.ActionPhoneCallback() {
                        @Override
                        public void onSuccessAction(Phone phone) {
                        }

                        @Override
                        public void onDataNotAvailable() {

                        }
                    });
                }

                @Override
                public void onDataNotAvailable() {

                }
            });
        }

        taskCompleteListener.onTaskComplete(true);
    }
}
