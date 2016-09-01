package ua.testapp.phonebook.repositories.contact.contracts;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import javax.inject.Inject;

import ua.testapp.phonebook.repositories.BaseEntry;

public class ContactContract {
    private static final String TAG = ContactContract.class.getSimpleName();

    @Inject
    public ContactContract() {}

    /* Inner class that defines the table contents */
    public static abstract class ContactEntry extends BaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "Contact";
        public static final String COLUMN_CONTACT_NAME = "name";
        public static final String COLUMN_CONTACT_SURNAME = "surname";
        public static final String COLUMN_PHOTO_PATH = "photo_path";
        public static final String COLUMN_POSITION = "position";
        public static final String COLUMN_CONTACT_LINK = "contact_link";
        public static final String COLUMN_EMAIL = "email";
    }

    private static String CREATE_CONTACT_TABLE = "CREATE TABLE " + ContactEntry.TABLE_NAME
            + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + ContactEntry.COLUMN_CONTACT_ID + " TEXT , "
            + ContactEntry.COLUMN_LOGIN + " TEXT , "
            + ContactEntry.COLUMN_CONTACT_NAME + " TEXT , "
            + ContactEntry.COLUMN_CONTACT_SURNAME + " TEXT , "
            + ContactEntry.COLUMN_PHOTO_PATH + " TEXT , "
            + ContactEntry.COLUMN_POSITION + " TEXT , "
            + ContactEntry.COLUMN_CONTACT_LINK + " TEXT , "
            + ContactEntry.COLUMN_EMAIL + " TEXT)" ;

    public static void createTable(SQLiteDatabase db) {
        Log.i(TAG, "Creating table contact");
        db.execSQL("DROP TABLE IF EXISTS " + ContactEntry.TABLE_NAME);
        db.execSQL(CREATE_CONTACT_TABLE);
    }
}
