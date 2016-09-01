package ua.testapp.phonebook.repositories.phone.contracts;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
import javax.inject.Inject;
import ua.testapp.phonebook.repositories.BaseEntry;

public class PhoneContract {
    private static final String TAG = PhoneContract.class.getSimpleName();

    @Inject
    public PhoneContract() {}

    /* Inner class that defines the table contents */
    public static abstract class PhoneEntry extends BaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "Phone";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PHONE_NUMBER= "phone_number";
    }

    private static String CREATE_PHONE_TABLE = "CREATE TABLE " + PhoneEntry.TABLE_NAME
            + " ( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + PhoneEntry.COLUMN_ID + " TEXT , "
            + PhoneEntry.COLUMN_CONTACT_ID + " TEXT , "
            + PhoneEntry.COLUMN_LOGIN + " TEXT , "
            + PhoneEntry.COLUMN_PHONE_NUMBER + " TEXT)" ;

    public static void createTable(SQLiteDatabase db) {
        Log.i(TAG, "Creating table phone");
        db.execSQL("DROP TABLE IF EXISTS " + PhoneEntry.TABLE_NAME);
        db.execSQL(CREATE_PHONE_TABLE);
    }
}
