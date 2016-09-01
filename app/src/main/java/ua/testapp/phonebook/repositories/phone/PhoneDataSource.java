package ua.testapp.phonebook.repositories.phone;

import android.support.annotation.NonNull;
import java.util.List;
import ua.testapp.phonebook.model.Phone;

public interface PhoneDataSource {

    interface ActionPhoneCallback {

        void onSuccessAction(Phone phone);

        void onDataNotAvailable();
    }

    List<Phone> getPhoneList(PhoneCriteria phoneCriteria);

    void getPhone(@NonNull String phoneId, @NonNull ActionPhoneCallback callback);

    void savePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback);

    void saveAllPhones(@NonNull List<Phone> phoneList);

    void updatePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback);

    void deleteAllPhones();

    void deletePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback);
}
