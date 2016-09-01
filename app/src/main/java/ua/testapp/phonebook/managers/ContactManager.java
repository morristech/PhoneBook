package ua.testapp.phonebook.managers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ua.testapp.phonebook.interfaces.TaskCompleteListener;
import ua.testapp.phonebook.model.Contact;
import ua.testapp.phonebook.repositories.contact.ContactCriteria;
import ua.testapp.phonebook.repositories.contact.ContactDataSource;
import ua.testapp.phonebook.repositories.contact.ContactRepository;
import ua.testapp.phonebook.repositories.phone.PhoneCriteria;
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


}
