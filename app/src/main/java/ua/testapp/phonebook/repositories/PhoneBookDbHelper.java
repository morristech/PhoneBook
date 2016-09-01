package ua.testapp.phonebook.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import javax.inject.Inject;
import ua.testapp.phonebook.constants.AppConfig;
import ua.testapp.phonebook.repositories.contact.contracts.ContactContract;
import ua.testapp.phonebook.repositories.phone.contracts.PhoneContract;
import ua.testapp.phonebook.repositories.user.contracts.UserContract;

public class PhoneBookDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = AppConfig.DB_VERSION;
    public static final String DATABASE_NAME = AppConfig.DB_NAME;

    @Inject
    UserContract mUserContract;

    @Inject
    ContactContract mContactContract;

    @Inject
    PhoneContract mPhoneContract;

    public PhoneBookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        mUserContract.createTable(db);
        mContactContract.createTable(db);
        mPhoneContract.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
