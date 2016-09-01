package ua.testapp.phonebook.ui;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.managers.ContactManager;
import ua.testapp.phonebook.utils.BitmapUtil;
import ua.testapp.phonebook.utils.DialogHelper;
import ua.testapp.phonebook.utils.ExternalStorageUtil;
import ua.testapp.phonebook.utils.FileUtil;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ContactDetailActivity_MembersInjector
    implements MembersInjector<ContactDetailActivity> {
  private final Provider<ContactManager> mContactManagerProvider;

  private final Provider<BitmapUtil> mBitmapUtilProvider;

  private final Provider<FileUtil> mFileUtilProvider;

  private final Provider<DialogHelper> mDialogHelperProvider;

  private final Provider<ExternalStorageUtil> mExternalStorageUtilProvider;

  public ContactDetailActivity_MembersInjector(
      Provider<ContactManager> mContactManagerProvider,
      Provider<BitmapUtil> mBitmapUtilProvider,
      Provider<FileUtil> mFileUtilProvider,
      Provider<DialogHelper> mDialogHelperProvider,
      Provider<ExternalStorageUtil> mExternalStorageUtilProvider) {
    assert mContactManagerProvider != null;
    this.mContactManagerProvider = mContactManagerProvider;
    assert mBitmapUtilProvider != null;
    this.mBitmapUtilProvider = mBitmapUtilProvider;
    assert mFileUtilProvider != null;
    this.mFileUtilProvider = mFileUtilProvider;
    assert mDialogHelperProvider != null;
    this.mDialogHelperProvider = mDialogHelperProvider;
    assert mExternalStorageUtilProvider != null;
    this.mExternalStorageUtilProvider = mExternalStorageUtilProvider;
  }

  public static MembersInjector<ContactDetailActivity> create(
      Provider<ContactManager> mContactManagerProvider,
      Provider<BitmapUtil> mBitmapUtilProvider,
      Provider<FileUtil> mFileUtilProvider,
      Provider<DialogHelper> mDialogHelperProvider,
      Provider<ExternalStorageUtil> mExternalStorageUtilProvider) {
    return new ContactDetailActivity_MembersInjector(
        mContactManagerProvider,
        mBitmapUtilProvider,
        mFileUtilProvider,
        mDialogHelperProvider,
        mExternalStorageUtilProvider);
  }

  @Override
  public void injectMembers(ContactDetailActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mContactManager = mContactManagerProvider.get();
    instance.mBitmapUtil = mBitmapUtilProvider.get();
    instance.mFileUtil = mFileUtilProvider.get();
    instance.mDialogHelper = mDialogHelperProvider.get();
    instance.mExternalStorageUtil = mExternalStorageUtilProvider.get();
  }

  public static void injectMContactManager(
      ContactDetailActivity instance, Provider<ContactManager> mContactManagerProvider) {
    instance.mContactManager = mContactManagerProvider.get();
  }

  public static void injectMBitmapUtil(
      ContactDetailActivity instance, Provider<BitmapUtil> mBitmapUtilProvider) {
    instance.mBitmapUtil = mBitmapUtilProvider.get();
  }

  public static void injectMFileUtil(
      ContactDetailActivity instance, Provider<FileUtil> mFileUtilProvider) {
    instance.mFileUtil = mFileUtilProvider.get();
  }

  public static void injectMDialogHelper(
      ContactDetailActivity instance, Provider<DialogHelper> mDialogHelperProvider) {
    instance.mDialogHelper = mDialogHelperProvider.get();
  }

  public static void injectMExternalStorageUtil(
      ContactDetailActivity instance, Provider<ExternalStorageUtil> mExternalStorageUtilProvider) {
    instance.mExternalStorageUtil = mExternalStorageUtilProvider.get();
  }
}
