package ua.testapp.phonebook.repositories.contact;

public class ContactCriteria {
    private String mSearchLine;

    public ContactCriteria(String searchLine) {
        mSearchLine = searchLine;
    }

    public String getSearchLine() {
        return mSearchLine;
    }
}
