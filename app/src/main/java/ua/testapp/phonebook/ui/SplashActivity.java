package ua.testapp.phonebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import javax.inject.Inject;

import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.R;
import ua.testapp.phonebook.constants.AppConfig;
import ua.testapp.phonebook.model.User;
import ua.testapp.phonebook.repositories.user.UserDataSource;
import ua.testapp.phonebook.repositories.user.UserRepository;
import ua.testapp.phonebook.utils.PreferenceHelper;

public class SplashActivity extends AbstractBaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Inject
    UserRepository mUserRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PhoneBookApplication) getApplicationContext()).getGeneralComponent().inject(this);
        actionRegisterFirstUser();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initContent() {
        new Handler().postDelayed(() -> actionAfterTimeOut(), AppConfig.SPLASH_TIME_OUT);
    }

    private void actionRegisterFirstUser() {
        if (PreferenceHelper.isFirstLogin()) {
            mUserRepository.signUp(new User("admin", "123456"), new UserDataSource.ActionUserCallback() {
                @Override
                public void onSuccessAction() {
                    Log.i(TAG, "User created success");
                    PreferenceHelper.setIsFirstLogin(false);
                }

                @Override
                public void onDataNotAvailable() {
                    Log.w(TAG, "This user was created earlier");
                    PreferenceHelper.setIsFirstLogin(true);
                }
            });
        }
    }

    private void actionAfterTimeOut() {
        if (PreferenceHelper.isAuth()) {
            actionToMain();
        } else {
            actionToLogin();
        }
    }

    private void actionToLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void actionToMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
