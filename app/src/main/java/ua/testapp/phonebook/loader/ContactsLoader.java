package ua.testapp.phonebook.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;
import javax.inject.Inject;
import de.greenrobot.event.EventBus;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.events.LoadContactsEvent;
import ua.testapp.phonebook.managers.ContactManager;
import ua.testapp.phonebook.model.Contact;

public class ContactsLoader extends AsyncTaskLoader<List<Contact>> {

    private static final String TAG = ContactsLoader.class.getSimpleName();

    @Inject
    ContactManager mContactManager;

    public ContactsLoader(Context context) {
        super(context);
        ((PhoneBookApplication) context.getApplicationContext()).getGeneralComponent().inject(this);
    }

    @Override
    protected void onStartLoading() {
        EventBus.getDefault().register(this);
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public List<Contact> loadInBackground() {
        return mContactManager.loadContacts(null);
    }

    public void onEvent(LoadContactsEvent event) {
        forceLoad();
    }

}
