package ua.testapp.phonebook.interfaces;

public interface ContactActionListener<Contact> {
    void onAddContact(Contact result);
    void onRemoveContact(Contact result);
}
