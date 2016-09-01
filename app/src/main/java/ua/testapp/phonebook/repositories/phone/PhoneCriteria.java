package ua.testapp.phonebook.repositories.phone;

public class PhoneCriteria {
    private String mContactId;

    public PhoneCriteria(String contactId) {
        mContactId = contactId;
    }

    public String getContactId() {
        return mContactId;
    }
}
