package ua.testapp.phonebook.repositories.phone;

import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import ua.testapp.phonebook.model.Phone;
import ua.testapp.phonebook.repositories.Local;
import ua.testapp.phonebook.repositories.Remote;

/**
 * Created by alexey on 28.08.16.
 */
public class PhoneRepository implements PhoneDataSource {

    private static final String TAG = PhoneRepository.class.getSimpleName();
    private final PhoneDataSource mPhoneLocalDataSource;
    private final PhoneDataSource mPhoneRemoteDataSource;

    @Inject
    PhoneRepository(@Remote PhoneDataSource phoneRemoteDataSource,
                    @Local PhoneDataSource phoneLocalDataSource) {
        mPhoneLocalDataSource = phoneLocalDataSource;

        /** use when need call api */
        mPhoneRemoteDataSource = phoneRemoteDataSource;
    }

    @Override
    public List<Phone> getPhoneList(PhoneCriteria phoneCriteria) {
        return mPhoneLocalDataSource.getPhoneList(phoneCriteria);
    }

    @Override
    public void getPhone(@NonNull String phoneId, @NonNull ActionPhoneCallback callback) {
        mPhoneLocalDataSource.getPhone(phoneId, new ActionPhoneCallback() {
            @Override
            public void onSuccessAction(Phone phone) {
                callback.onSuccessAction(phone);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void savePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback) {
        mPhoneLocalDataSource.savePhone(phone, new ActionPhoneCallback() {
            @Override
            public void onSuccessAction(Phone phone) {
                callback.onSuccessAction(phone);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveAllPhones(@NonNull List<Phone> phoneList) {
        mPhoneLocalDataSource.saveAllPhones(phoneList);
    }

    @Override
    public void updatePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback) {
        mPhoneLocalDataSource.updatePhone(phone, new ActionPhoneCallback() {
            @Override
            public void onSuccessAction(Phone phone) {
                callback.onSuccessAction(phone);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void deleteAllPhones() {
        mPhoneLocalDataSource.deleteAllPhones();
    }

    @Override
    public void deletePhone(@NonNull Phone phone, @NonNull ActionPhoneCallback callback) {
        mPhoneLocalDataSource.deletePhone(phone, new ActionPhoneCallback() {
            @Override
            public void onSuccessAction(Phone phone) {
                callback.onSuccessAction(phone);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
