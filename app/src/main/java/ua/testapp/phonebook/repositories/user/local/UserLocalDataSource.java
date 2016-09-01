package ua.testapp.phonebook.repositories.user.local;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.model.User;
import ua.testapp.phonebook.repositories.PhoneBookDbHelper;
import ua.testapp.phonebook.repositories.user.UserDataSource;
import ua.testapp.phonebook.repositories.user.contracts.UserContract;

public class UserLocalDataSource implements UserDataSource {

    private static final String TAG = UserLocalDataSource.class.getSimpleName();

    private PhoneBookDbHelper mDbHelper;

    public UserLocalDataSource(@NonNull Context context) {
        mDbHelper = new PhoneBookDbHelper(context);
        ((PhoneBookApplication) context).getGeneralComponent().inject(this);
    }

    @Override
    public void signIn(@NonNull User user, @NonNull ActionUserCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        getUser(db, user, new ActionUserCallback(){

            @Override
            public void onSuccessAction() {
                callback.onSuccessAction();
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
        db.close();
    }

    @Override
    public void signUp(@NonNull User user, @NonNull ActionUserCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            ContentValues values = user.geCredentialContentValues();
            boolean isSave = db.insert(UserContract.UserEntry.TABLE_NAME, null, values) != -1;
            if (isSave) {
                callback.onSuccessAction();
            } else {
                callback.onDataNotAvailable();
            }
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
            callback.onDataNotAvailable();
        }

        db.close();
    }

    public void updateUser(@NonNull User user, @NonNull ActionUserCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        updateUser(db, user, callback);
        db.close();
    }

    public void deleteUser(@NonNull User user, @NonNull ActionUserCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        boolean isDeleted = deleteUser(db, user);
        db.close();
        if (isDeleted) {
            callback.onSuccessAction();
        } else {
            callback.onDataNotAvailable();
        }
    }

    private void getUser(@NonNull SQLiteDatabase db, @NonNull User user, final @NonNull ActionUserCallback callback) {

        String selection = UserContract.UserEntry.COLUMN_LOGIN + "='" + user.getLogin() + "'"
                + " AND "
                + UserContract.UserEntry.COLUMN_PASSWORD + "=" + "'" + user.getPassword() + "'";

        Cursor cursor =  db.rawQuery("select * from " + UserContract.UserEntry.TABLE_NAME + " where " + selection, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            callback.onSuccessAction();
        } else {
            callback.onDataNotAvailable();
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private void updateUser(SQLiteDatabase db, User user, ActionUserCallback callback) {
        ContentValues values = user.geCredentialContentValues();
        String selection = UserContract.UserEntry.COLUMN_LOGIN + "='" + user.getLogin() + "'";
        int isUpdate = db.update(UserContract.UserEntry.TABLE_NAME, values, selection, null);

        if (isUpdate == -1) {
            callback.onDataNotAvailable();
        } else {
            callback.onSuccessAction();
        }
    }

    private boolean deleteUser(@NonNull SQLiteDatabase db, @NonNull User user) {
        return db.delete(UserContract.UserEntry.TABLE_NAME, UserContract.UserEntry.COLUMN_LOGIN + "= ?", new String[]{user.getLogin()}) > 0;
    }
}
