package ua.testapp.phonebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.R;
import ua.testapp.phonebook.model.User;
import ua.testapp.phonebook.repositories.user.UserDataSource;
import ua.testapp.phonebook.repositories.user.UserRepository;
import ua.testapp.phonebook.utils.DialogHelper;
import ua.testapp.phonebook.utils.PreferenceHelper;
import ua.testapp.phonebook.utils.UIHelper;

public class LoginActivity extends AbstractBaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.root_view)
    View rootView;

    @BindView(R.id.tv_logo)
    TextView tvLogo;

    @BindView(R.id.et_login)
    EditText etLogin;

    @BindView(R.id.et_pass)
    EditText etPassword;

    @BindView(R.id.ll_container)
    LinearLayout llInputContainer;

    @BindView(R.id.tv_sign_in)
    TextView tvSignIn;

    @Inject
    DialogHelper mDialogHelper;

    @Inject
    UserRepository mUserRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PhoneBookApplication) getApplicationContext()).getGeneralComponent().inject(this);
        mDialogHelper.setContext(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initContent() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animator_down_up);
        llInputContainer.startAnimation(anim);
    }

    @OnClick(R.id.tv_sign_in)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_in:
                actionSignIn();
                break;
            default:
        }
    }

    private void actionSignIn() {

        // get data login pass
        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        //start sign in
        if (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password)) {
            //progress bar sign in
            showProcessDialog();

            mUserRepository.signIn(new User(login, password), new UserDataSource.ActionUserCallback() {
                @Override
                public void onSuccessAction() {
                    // init preference
                    PreferenceHelper.setUserLogin(login);
                    PreferenceHelper.setIsAuth(true);

                    // go to main
                    actionToMain();
                }

                @Override
                public void onDataNotAvailable() {
                    showErrorDialog(getString(R.string.error_dialog_message__forbidden));
                }
            });
        } else {
            UIHelper.showSnackbar(rootView, getString(R.string.error_dialog_message__valid_login_pass));
        }
    }

    private void showProcessDialog() {
        mDialogHelper.dismissDialog();
        mDialogHelper.showProgressDialog();
    }

    private void showErrorDialog(String message) {
        mDialogHelper.dismissDialog();
        mDialogHelper.showErrorDialog(getString(R.string.error_dialog_title__sign_in_fail), message);
    }

    private void actionToMain() {
        mDialogHelper.dismissDialog();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
