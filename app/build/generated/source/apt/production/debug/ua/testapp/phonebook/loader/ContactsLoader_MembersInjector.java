package ua.testapp.phonebook.loader;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.managers.ContactManager;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ContactsLoader_MembersInjector implements MembersInjector<ContactsLoader> {
  private final Provider<ContactManager> mContactManagerProvider;

  public ContactsLoader_MembersInjector(Provider<ContactManager> mContactManagerProvider) {
    assert mContactManagerProvider != null;
    this.mContactManagerProvider = mContactManagerProvider;
  }

  public static MembersInjector<ContactsLoader> create(
      Provider<ContactManager> mContactManagerProvider) {
    return new ContactsLoader_MembersInjector(mContactManagerProvider);
  }

  @Override
  public void injectMembers(ContactsLoader instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mContactManager = mContactManagerProvider.get();
  }

  public static void injectMContactManager(
      ContactsLoader instance, Provider<ContactManager> mContactManagerProvider) {
    instance.mContactManager = mContactManagerProvider.get();
  }
}
