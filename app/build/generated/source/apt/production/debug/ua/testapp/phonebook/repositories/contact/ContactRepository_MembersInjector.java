package ua.testapp.phonebook.repositories.contact;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.utils.ExternalStorageUtil;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ContactRepository_MembersInjector implements MembersInjector<ContactRepository> {
  private final Provider<ExternalStorageUtil> mExternalStorageUtilProvider;

  public ContactRepository_MembersInjector(
      Provider<ExternalStorageUtil> mExternalStorageUtilProvider) {
    assert mExternalStorageUtilProvider != null;
    this.mExternalStorageUtilProvider = mExternalStorageUtilProvider;
  }

  public static MembersInjector<ContactRepository> create(
      Provider<ExternalStorageUtil> mExternalStorageUtilProvider) {
    return new ContactRepository_MembersInjector(mExternalStorageUtilProvider);
  }

  @Override
  public void injectMembers(ContactRepository instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mExternalStorageUtil = mExternalStorageUtilProvider.get();
  }

  public static void injectMExternalStorageUtil(
      ContactRepository instance, Provider<ExternalStorageUtil> mExternalStorageUtilProvider) {
    instance.mExternalStorageUtil = mExternalStorageUtilProvider.get();
  }
}
