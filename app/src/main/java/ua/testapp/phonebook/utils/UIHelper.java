package ua.testapp.phonebook.utils;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.R;

public class UIHelper {

    private static Context mContext = PhoneBookApplication.getContext();

    public static void showSnackbar(View rootView, String message) {
        final Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.setAction(mContext.getResources().getString(R.string.okay), view -> snackbar.dismiss());

        snackbar.show();
    }

    public static void hideKeyboard(final FragmentActivity fragmentActivity) {
        new Handler().postDelayed(() -> {
            {
                InputMethodManager imm = (InputMethodManager) fragmentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);

                if (fragmentActivity.getCurrentFocus() != null
                        && (fragmentActivity.getCurrentFocus().getWindowToken() != null)) {
                    imm.hideSoftInputFromWindow(fragmentActivity.getCurrentFocus().getWindowToken(), 0);
                }
            }
        }, 200);
    }

    public static void showKeyboard(final FragmentActivity fragmentActivity) {
        InputMethodManager imm = (InputMethodManager) fragmentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 1);
    }

    public static void showShortToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
}
