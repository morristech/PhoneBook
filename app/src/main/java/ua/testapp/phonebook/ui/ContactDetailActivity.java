package ua.testapp.phonebook.ui;

import android.os.Bundle;

import ua.testapp.phonebook.R;

/**
 * Created by alexey on 26.08.16.
 */
public class ContactDetailActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public int getContentView() {
        return R.layout.activity_contact_detail;
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initContent() {

    }
}
