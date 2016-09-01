package ua.testapp.phonebook.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {

    private final Gson mGson;

    public GsonModule() {
        mGson = new GsonBuilder().serializeNulls().create();
    }

    @Provides
    public Gson getGson() {
        return mGson;
    }
}
