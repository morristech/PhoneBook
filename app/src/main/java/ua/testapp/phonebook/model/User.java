package ua.testapp.phonebook.model;

import android.content.ContentValues;
import ua.testapp.phonebook.repositories.user.contracts.UserContract;

/**
 * Created by alexey on 26.08.16.
 */
public class User {
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public ContentValues geCredentialContentValues () {
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_LOGIN, this.getLogin());
        values.put(UserContract.UserEntry.COLUMN_PASSWORD, this.getPassword());
        return values;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        return login != null ? login.equals(that.login) : that.login == null;

    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }
}
