package ua.testapp.phonebook.repositories.phone.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import java.util.List;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.model.Phone;
import ua.testapp.phonebook.repositories.phone.PhoneCriteria;
import ua.testapp.phonebook.repositories.phone.PhoneDataSource;

public class PhoneRemoteDataSource implements PhoneDataSource {
    private static final String TAG = PhoneRemoteDataSource.class.getSimpleName();

    public PhoneRemoteDataSource(@NonNull Context context) {
        ((PhoneBookApplication) context).getGeneralComponent().inject(this);
    }

    @Override
    public List<Phone> getPhoneList(PhoneCriteria phoneCriteria) {
        //call server
        return null;
    }

    @Override
    public void getPhone(@NonNull String phoneId, @NonNull ActionPhoneCallback callback) {
        //call server
    }

    @Override
    public void savePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback) {
        //call server
    }

    @Override
    public void saveAllPhones(@NonNull List<Phone> phoneList) {
        //call server
    }

    @Override
    public void updatePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback) {
        //call server
    }

    @Override
    public void deleteAllPhones() {
        //call server
    }

    @Override
    public void deletePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback) {
        //call server
    }
}
