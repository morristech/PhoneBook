package ua.testapp.phonebook.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

import ua.testapp.phonebook.repositories.BaseEntry;
import ua.testapp.phonebook.repositories.contact.contracts.ContactContract;
import ua.testapp.phonebook.repositories.phone.contracts.PhoneContract;

/**
 * Created by alexey on 26.08.16.
 */
public class Contact implements Parcelable {

    private String id;
    private String name;
    private String surname;
    private String photoPath;
    private String position;
    private String contactLink;
    private String email;
    private List<Phone> phoneList;

    public Contact() {

    }

    public Contact(Cursor cursor) {
        //read cursor
        String contactId = cursor.getString(cursor.getColumnIndexOrThrow(BaseEntry.COLUMN_CONTACT_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_CONTACT_NAME));
        String surname = cursor.getString(cursor.getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_CONTACT_SURNAME));
        String photoPath = cursor.getString(cursor.getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_PHOTO_PATH));
        String position = cursor.getString(cursor.getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_POSITION));
        String contactLink = cursor.getString(cursor.getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_CONTACT_LINK));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_EMAIL));

        //create phone object
        this.setId(contactId);
        this.setName(name);
        this.setSurname(surname);
        this.setPhotoPath(photoPath);
        this.setPosition(position);
        this.setContactLink(contactLink);
        this.setEmail(email);
    }

    public ContentValues getContactContentValues () {
        ContentValues values = new ContentValues();
        values.put(BaseEntry.COLUMN_CONTACT_ID, this.getId());
        values.put(ContactContract.ContactEntry.COLUMN_CONTACT_NAME, this.getName());
        values.put(ContactContract.ContactEntry.COLUMN_CONTACT_SURNAME, this.getSurname());
        values.put(ContactContract.ContactEntry.COLUMN_PHOTO_PATH, this.getPhotoPath());
        values.put(ContactContract.ContactEntry.COLUMN_POSITION, this.getPosition());
        values.put(ContactContract.ContactEntry.COLUMN_CONTACT_LINK, this.getContactLink());
        values.put(ContactContract.ContactEntry.COLUMN_EMAIL, this.getEmail());

        return values;
    }

    protected Contact(Parcel in) {
        id = in.readString();
        name = in.readString();
        surname = in.readString();
        photoPath = in.readString();
        position = in.readString();
        contactLink = in.readString();
        email = in.readString();
        phoneList = in.createTypedArrayList(Phone.CREATOR);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContactLink() {
        return contactLink;
    }

    public void setContactLink(String contactLink) {
        this.contactLink = contactLink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return id == contact.id;

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", position='" + position + '\'' +
                ", contactLink='" + contactLink + '\'' +
                ", email='" + email + '\'' +
                ", phoneList=" + phoneList +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(photoPath);
        parcel.writeString(position);
        parcel.writeString(contactLink);
        parcel.writeString(email);
        parcel.writeTypedList(phoneList);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
