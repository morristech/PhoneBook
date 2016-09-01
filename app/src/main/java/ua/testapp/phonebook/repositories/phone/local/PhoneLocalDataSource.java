package ua.testapp.phonebook.repositories.phone.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.model.Phone;
import ua.testapp.phonebook.repositories.BaseEntry;
import ua.testapp.phonebook.repositories.PhoneBookDbHelper;
import ua.testapp.phonebook.repositories.phone.PhoneCriteria;
import ua.testapp.phonebook.repositories.phone.PhoneDataSource;
import ua.testapp.phonebook.repositories.phone.contracts.PhoneContract;
import ua.testapp.phonebook.utils.PreferenceHelper;

public class PhoneLocalDataSource implements PhoneDataSource {

    private static final String TAG = PhoneLocalDataSource.class.getSimpleName();

    private PhoneBookDbHelper mDbHelper;

    public PhoneLocalDataSource(@NonNull Context context) {
        mDbHelper = new PhoneBookDbHelper(context);
        ((PhoneBookApplication) context).getGeneralComponent().inject(this);
    }


    @Override
    public List<Phone> getPhoneList(PhoneCriteria phoneCriteria) {
        List<Phone> phoneList = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] phoneData = { PhoneContract.PhoneEntry.COLUMN_ID ,
                PhoneContract.PhoneEntry.COLUMN_CONTACT_ID ,
                PhoneContract.PhoneEntry.COLUMN_LOGIN ,
                PhoneContract.PhoneEntry.COLUMN_PHONE_NUMBER };

        String filterPhones = "";
        String orderByBaseId = BaseColumns._ID + " ASC";

        if (phoneCriteria != null) {
            if (!TextUtils.isEmpty(phoneCriteria.getContactId())) {
                filterPhones = BaseEntry.COLUMN_CONTACT_ID + "=" + "'" + phoneCriteria.getContactId() + "'" + " AND ";
            }
        }

        filterPhones += BaseEntry.COLUMN_LOGIN + "=" + "'" + PreferenceHelper.getUserLogin() + "'" ;

        Cursor cursor = db.query(PhoneContract.PhoneEntry.TABLE_NAME,
                phoneData,
                filterPhones,
                null, null, null,
                orderByBaseId);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                //add data in list
                phoneList.add(new Phone(cursor));
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        if (phoneList.isEmpty()) {
            // This will be called if the table is new or just empty.
            return Collections.EMPTY_LIST;
        } else {
            return phoneList;
        }
    }

    @Override
    public void getPhone(@NonNull String phoneId, @NonNull ActionPhoneCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        getPhone(db, phoneId, new ActionPhoneCallback(){

            @Override
            public void onSuccessAction(Phone phone) {
                callback.onSuccessAction(phone);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
        db.close();
    }

    @Override
    public void savePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            ContentValues values = phone.getPhoneContentValues();
            putLoginToContentValues(values);
            boolean isSave = db.insert(PhoneContract.PhoneEntry.TABLE_NAME, null, values) != -1;
            if (isSave) {
                callback.onSuccessAction(phone);
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
    public void saveAllPhones(@NonNull List<Phone> phoneList) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.beginTransaction();

        try {
            for (final Phone phoneTemp : phoneList) {
                final ContentValues values = phoneTemp.getPhoneContentValues();
                putLoginToContentValues(values);

                getPhone(db, phoneTemp.getId(), new ActionPhoneCallback() {
                    @Override
                    public void onSuccessAction(Phone phone) {
                        Log.i(TAG, "phone contains in db");

                        updatePhone(db, phoneTemp, new ActionPhoneCallback() {
                            @Override
                            public void onSuccessAction(Phone phone) {
                                Log.i(TAG, "phone updated in db");

                            }

                            @Override
                            public void onDataNotAvailable() {
                                Log.i(TAG, "phone didn't update in db");
                            }
                        });
                    }

                    @Override
                    public void onDataNotAvailable() {
                        db.insert(PhoneContract.PhoneEntry.TABLE_NAME, null, values);
                        Log.i(TAG, "create new phone number in db");
                    }
                });
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    @Override
    public void updatePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        updatePhone(db, phone, callback);
        db.close();
    }

    @Override
    public void deleteAllPhones() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(PhoneContract.PhoneEntry.TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void deletePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        boolean isDeleted = deletePhone(db, phone);
        db.close();
        if (isDeleted) {
            callback.onSuccessAction(phone);
        } else {
            callback.onDataNotAvailable();
        }
    }

    private void getPhone(@NonNull SQLiteDatabase db, @NonNull String phoneId, final @NonNull ActionPhoneCallback callback) {

        String selection = PhoneContract.PhoneEntry.COLUMN_ID + "='" + phoneId + "'"
                + " AND "
                + BaseEntry.COLUMN_LOGIN + "=" + "'" + PreferenceHelper.getUserLogin() + "'";

        Cursor cursor =  db.rawQuery("select * from " + PhoneContract.PhoneEntry.TABLE_NAME + " where " + selection, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            callback.onSuccessAction(new Phone(cursor));
        } else {
            callback.onDataNotAvailable();
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private boolean deletePhone(@NonNull SQLiteDatabase db, @NonNull Phone phone) {
        return db.delete(PhoneContract.PhoneEntry.TABLE_NAME, PhoneContract.PhoneEntry.COLUMN_ID + "= ?", new String[]{phone.getId()}) > 0;
    }

    private void updatePhone(SQLiteDatabase db, Phone phone, ActionPhoneCallback callback) {
        ContentValues values = phone.getPhoneContentValues();
        putLoginToContentValues(values);
        String selection = PhoneContract.PhoneEntry.COLUMN_ID + "='" + phone.getId() + "'"
                + " AND "
                + BaseEntry.COLUMN_LOGIN + "=" + "'" + PreferenceHelper.getUserLogin() + "'";
        int isUpdate = db.update(PhoneContract.PhoneEntry.TABLE_NAME, values, selection, null);

        if (isUpdate == -1) {
            callback.onDataNotAvailable();
        } else {
            callback.onSuccessAction(phone);
        }
    }

    private void putLoginToContentValues(ContentValues values) {
        if (values != null) {
            values.put(BaseEntry.COLUMN_LOGIN, PreferenceHelper.getUserLogin());
        }
    }
}
