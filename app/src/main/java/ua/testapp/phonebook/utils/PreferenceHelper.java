package ua.testapp.phonebook.utils;

import android.content.Context;
import android.content.SharedPreferences;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.constants.Prefs;
/**
 * Created by alexey on 26.08.16.
 */
public class PreferenceHelper {

    private static Context mContext = PhoneBookApplication.getContext();

    public static boolean isFirstLogin() {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        return settings.getBoolean(Prefs.IS_FIRST_LOGIN, true);
    }

    public static void setIsFirstLogin(boolean isFirstLogin) {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        settings.edit().putBoolean(Prefs.IS_FIRST_LOGIN, isFirstLogin).apply();
    }

    public static boolean isAuth() {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        return settings.getBoolean(Prefs.IS_AUTH, false);
    }

    public static void setIsAuth(boolean isAuth) {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        settings.edit().putBoolean(Prefs.IS_AUTH, isAuth).apply();
    }

    public static String getUserLogin() {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        return settings.getString(Prefs.USER_LOGIN, "");
    }

    public static void setUserLogin(String userLogin) {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        settings.edit().putString(Prefs.USER_LOGIN, userLogin).apply();
    }

    public static String getUserName() {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        return settings.getString(Prefs.USER_NAME, "");
    }

    public static void setUserName(String userName) {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        settings.edit().putString(Prefs.USER_NAME, userName).apply();
    }

    public static String getUserSurname() {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        return settings.getString(Prefs.USER_SURNAME, "");
    }

    public static void setUserSurname(String userSurname) {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        settings.edit().putString(Prefs.USER_SURNAME, userSurname).apply();
    }

    public static String getUserPhoneNumber() {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        return settings.getString(Prefs.USER_PHONE_NUMBER, "");
    }

    public static void setUserPhoneNumber(String userPhoneNumber) {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        settings.edit().putString(Prefs.USER_PHONE_NUMBER, userPhoneNumber).apply();
    }

    public static String getUserPhotoPath() {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        return settings.getString(Prefs.USER_PHOTO_PATH, "");
    }

    public static void setUserPhotoPath(String userPhotoPath) {
        SharedPreferences settings = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE);
        settings.edit().putString(Prefs.USER_PHOTO_PATH, userPhotoPath).apply();
    }

    public static void clearInfo() {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(Prefs.PREFS_APP_DATA, Context.MODE_PRIVATE).edit();
        editor.remove(Prefs.USER_NAME);
        editor.remove(Prefs.USER_SURNAME);
        editor.remove(Prefs.USER_LOGIN);
        editor.remove(Prefs.USER_PHONE_NUMBER);
        editor.remove(Prefs.USER_PHOTO_PATH);
        editor.remove(Prefs.IS_AUTH);
        editor.apply();
    }


}
