package ua.testapp.phonebook;

import android.app.Application;
import android.content.Context;

import ua.testapp.phonebook.component.DaggerGeneralComponent;
import ua.testapp.phonebook.component.GeneralComponent;
import ua.testapp.phonebook.module.ApplicationContextModule;

/**
 * Created by alexey on 26.08.16.
 */
public class PhoneBookApplication extends Application {
    private static Context mContext;
    private GeneralComponent mGeneralComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //init flurry analytics
        initFlurryAnalytics();

        mContext = getApplicationContext();

        mGeneralComponent = DaggerGeneralComponent.builder().applicationContextModule(new ApplicationContextModule(getApplicationContext())).build();

    }

    private void initFlurryAnalytics() {
        /*new FlurryAgent.Builder()
                .withLogEnabled(false)
                .build(this, getString(R.string.key_flurry_analytics));*/
    }

    public static Context getContext() {
        return mContext;
    }

    public GeneralComponent getGeneralComponent() {
        return mGeneralComponent;
    }

}
