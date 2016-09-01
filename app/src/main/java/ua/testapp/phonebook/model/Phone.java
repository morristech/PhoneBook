package ua.testapp.phonebook.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import ua.testapp.phonebook.repositories.BaseEntry;
import ua.testapp.phonebook.repositories.phone.contracts.PhoneContract;

/**
 * Created by alexey on 26.08.16.
 */
public class Phone implements Parcelable {
    private String id;
    private String contactId;
    private String phoneNumber;

    public Phone () {

    }

    public Phone(Cursor cursor) {
        //read cursor
        String phoneId = cursor.getString(cursor.getColumnIndexOrThrow(PhoneContract.PhoneEntry.COLUMN_ID));
        String contactId = cursor.getString(cursor.getColumnIndexOrThrow(PhoneContract.PhoneEntry.COLUMN_CONTACT_ID));
        String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(PhoneContract.PhoneEntry.COLUMN_PHONE_NUMBER));

        //create phone object
        this.setId(phoneId);
        this.setContactId(contactId);
        this.setPhoneNumber(phoneNumber);
    }

    public ContentValues getPhoneContentValues () {
        ContentValues values = new ContentValues();
        values.put(PhoneContract.PhoneEntry.COLUMN_ID, this.getId());
        values.put(PhoneContract.PhoneEntry.COLUMN_CONTACT_ID, this.getContactId());
        values.put(PhoneContract.PhoneEntry.COLUMN_PHONE_NUMBER, this.getPhoneNumber());
        return values;
    }

    protected Phone(Parcel in) {
        id = in.readString();
        contactId = in.readString();
        phoneNumber = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id='" + id + '\'' +
                ", contactId='" + contactId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        return id == phone.id;

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(contactId);
        parcel.writeString(phoneNumber);
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}
