package ua.testapp.phonebook.repositories.contact.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.model.Contact;
import ua.testapp.phonebook.repositories.BaseEntry;
import ua.testapp.phonebook.repositories.PhoneBookDbHelper;
import ua.testapp.phonebook.repositories.contact.ContactCriteria;
import ua.testapp.phonebook.repositories.contact.ContactDataSource;
import ua.testapp.phonebook.repositories.contact.contracts.ContactContract;
import ua.testapp.phonebook.utils.PreferenceHelper;

public class ContactLocalDataSource implements ContactDataSource {

    private static final String TAG = ContactLocalDataSource.class.getSimpleName();

    private PhoneBookDbHelper mDbHelper;

    public ContactLocalDataSource(@NonNull Context context) {
        mDbHelper = new PhoneBookDbHelper(context);
        ((PhoneBookApplication) context).getGeneralComponent().inject(this);
    }


    @Override
    public List<Contact> getContactList(ContactCriteria contactCriteria) {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] contactData = { ContactContract.ContactEntry.COLUMN_CONTACT_ID ,
                ContactContract.ContactEntry.COLUMN_LOGIN ,
                ContactContract.ContactEntry.COLUMN_CONTACT_NAME ,
                ContactContract.ContactEntry.COLUMN_CONTACT_SURNAME ,
                ContactContract.ContactEntry.COLUMN_PHOTO_PATH ,
                ContactContract.ContactEntry.COLUMN_POSITION ,
                ContactContract.ContactEntry.COLUMN_CONTACT_LINK ,
                ContactContract.ContactEntry.COLUMN_EMAIL };

        String filterContacts = "";
        String orderBySurname = ContactContract.ContactEntry.COLUMN_CONTACT_NAME + " ASC";

        if (contactCriteria != null) {
            if (!TextUtils.isEmpty(contactCriteria.getSearchLine())) {
                filterContacts = ContactContract.ContactEntry.COLUMN_CONTACT_NAME + " LIKE '" + contactCriteria.getSearchLine() + "%' OR "
                         + ContactContract.ContactEntry.COLUMN_CONTACT_SURNAME + " LIKE '" + contactCriteria.getSearchLine() + "%' AND ";
            }
        }

        filterContacts += ContactContract.ContactEntry.COLUMN_LOGIN + "=" + "'" + PreferenceHelper.getUserLogin() + "'" ;

        Cursor cursor = db.query(ContactContract.ContactEntry.TABLE_NAME,
                contactData,
                filterContacts,
                null, null, null,
                orderBySurname);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                //add data in list
                contactList.add(new Contact(cursor));
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        if (contactList.isEmpty()) {
            // This will be called if the table is new or just empty.
            return Collections.EMPTY_LIST;
        } else {
            return contactList;
        }
    }

    @Override
    public void getContact(@NonNull String contactId, @NonNull ActionContactCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        getContact(db, contactId, new ActionContactCallback(){

            @Override
            public void onSuccessAction(Contact contact) {
                callback.onSuccessAction(contact);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
        db.close();
    }

    @Override
    public void saveContact(@NonNull Contact contact, @NonNull ActionContactCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            ContentValues values = contact.getContactContentValues();
            putLoginToContentValues(values);
            boolean isSave = db.insert(ContactContract.ContactEntry.TABLE_NAME, null, values) != -1;
            if (isSave) {
                callback.onSuccessAction(contact);
            } else {
                callback.onDataNotAvailable();
            }
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
            callback.onDataNotAvailable();
        }

        db.close();
    }

    @Override
    public boolean saveAllContacts(@NonNull List<Contact> phoneList) {
        return true;
    }

    @Override
    public void updateContact(@NonNull Contact contact, @NonNull ActionContactCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        updateContact(db, contact, callback);
        db.close();
    }

    @Override
    public boolean deleteAllContacts() {
        return true;
    }

    @Override
    public void deleteContact(@NonNull Contact contact, @NonNull ActionContactCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        boolean isDeleted = deleteContact(db, contact);
        db.close();
        if (isDeleted) {
            callback.onSuccessAction(contact);
        } else {
            callback.onDataNotAvailable();
        }
    }

    private void getContact(@NonNull SQLiteDatabase db, @NonNull String contactId, final @NonNull ActionContactCallback contactCallback) {

        String selection = ContactContract.ContactEntry.COLUMN_CONTACT_ID + "='" + contactId + "'"
                + " AND "
                + BaseEntry.COLUMN_LOGIN + "=" + "'" + PreferenceHelper.getUserLogin() + "'";

        Cursor cursor =  db.rawQuery("select * from " + ContactContract.ContactEntry.TABLE_NAME + " where " + selection, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            contactCallback.onSuccessAction(new Contact(cursor));
        } else {
            contactCallback.onDataNotAvailable();
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private boolean deleteContact(@NonNull SQLiteDatabase db, @NonNull Contact contact) {
        return db.delete(ContactContract.ContactEntry.TABLE_NAME, ContactContract.ContactEntry.COLUMN_CONTACT_ID + "= ?", new String[]{contact.getId()}) > 0;
    }

    private void updateContact(SQLiteDatabase db, Contact contact, ActionContactCallback callback) {
        ContentValues values = contact.getContactContentValues();
        putLoginToContentValues(values);
        String selection = ContactContract.ContactEntry.COLUMN_CONTACT_ID + "='" + contact.getId() + "'"
                + " AND "
                + BaseEntry.COLUMN_LOGIN + "=" + "'" + PreferenceHelper.getUserLogin() + "'";
        int isUpdate = db.update(ContactContract.ContactEntry.TABLE_NAME, values, selection, null);

        if (isUpdate == -1) {
            callback.onDataNotAvailable();
        } else {
            callback.onSuccessAction(contact);
        }
    }

    private void putLoginToContentValues(ContentValues values) {
        if (values != null) {
            values.put(BaseEntry.COLUMN_LOGIN, PreferenceHelper.getUserLogin());
        }
    }
}
