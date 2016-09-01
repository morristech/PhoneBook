package ua.testapp.phonebook.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationContextModule {
    private final Context mAppContext;

    public ApplicationContextModule(Context appContext) {
        mAppContext = appContext;
    }

    @Provides
    public Context getAppContext() {
        return mAppContext;
    }
}
