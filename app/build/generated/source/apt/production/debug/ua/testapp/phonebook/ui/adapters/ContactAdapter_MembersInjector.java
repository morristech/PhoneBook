package ua.testapp.phonebook.ui.adapters;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.utils.FileUtil;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ContactAdapter_MembersInjector implements MembersInjector<ContactAdapter> {
  private final Provider<FileUtil> mFileUtilProvider;

  public ContactAdapter_MembersInjector(Provider<FileUtil> mFileUtilProvider) {
    assert mFileUtilProvider != null;
    this.mFileUtilProvider = mFileUtilProvider;
  }

  public static MembersInjector<ContactAdapter> create(Provider<FileUtil> mFileUtilProvider) {
    return new ContactAdapter_MembersInjector(mFileUtilProvider);
  }

  @Override
  public void injectMembers(ContactAdapter instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mFileUtil = mFileUtilProvider.get();
  }

  public static void injectMFileUtil(
      ContactAdapter instance, Provider<FileUtil> mFileUtilProvider) {
    instance.mFileUtil = mFileUtilProvider.get();
  }
}
