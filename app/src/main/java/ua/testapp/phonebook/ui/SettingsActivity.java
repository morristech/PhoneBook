package ua.testapp.phonebook.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;
import ua.testapp.phonebook.R;
import ua.testapp.phonebook.constants.AppConfig;
import ua.testapp.phonebook.ui.customView.EditTextSimpleError;
import ua.testapp.phonebook.utils.PreferenceHelper;
import ua.testapp.phonebook.utils.UIHelper;
import ua.testapp.phonebook.utils.UsPhoneNumberFormatter;

/**
 * Created by alexey on 26.08.16.
 */
public class SettingsActivity extends AbstractBaseActivity {

    @BindView(R.id.root_view)
    View rootView;

    @BindView(R.id.fab_save)
    FloatingActionButton fabSave;

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_surname)
    EditText etSurname;

    @BindView(R.id.et_phone_number)
    EditTextSimpleError etPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_settings;
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void initContent() {
        setTitleToolBar(getString(R.string.settings));

        etName.setText(PreferenceHelper.getUserName());
        etSurname.setText(PreferenceHelper.getUserSurname());
        etPhoneNumber.setText(PreferenceHelper.getUserPhoneNumber());

        UsPhoneNumberFormatter addLineNumberFormatter = new UsPhoneNumberFormatter(new WeakReference<>(etPhoneNumber), etPhoneNumber);
        etPhoneNumber.addTextChangedListener(addLineNumberFormatter);
    }

    @OnClick(R.id.fab_save)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_save:
                actionSaveUserData();
                break;
            default:
        }
    }

    private void actionSaveUserData() {

        if (TextUtils.isEmpty(etPhoneNumber.getText().toString()) || etPhoneNumber.getText().toString().length() == AppConfig.PHONE_NUMBER_LENGTH) {
            PreferenceHelper.setUserName(etName.getText().toString());
            PreferenceHelper.setUserSurname(etSurname.getText().toString());
            PreferenceHelper.setUserPhoneNumber(etPhoneNumber.getText().toString());
            finish();
        } else {
            etPhoneNumber.setError("");
            UIHelper.showSnackbar(rootView, getString(R.string.error_phone_number_format));
        }
    }
}
