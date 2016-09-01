package ua.testapp.phonebook.repositories.user.contracts;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
import javax.inject.Inject;

public class UserContract {
    private static final String TAG = UserContract.class.getSimpleName();

    @Inject
    public UserContract() {}

    /* Inner class that defines the table contents */
    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_PASSWORD= "password";
    }

    private static String CREATE_USER_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME
            + " ( " + BaseColumns._ID + " INTEGER AUTO_INCREMENT ,"
            + UserEntry.COLUMN_LOGIN + " TEXT PRIMARY KEY , "
            + UserEntry.COLUMN_PASSWORD + " TEXT)" ;

    public static void createTable(SQLiteDatabase db) {
        Log.i(TAG, "Creating table user");
        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        db.execSQL(CREATE_USER_TABLE);
    }
}
