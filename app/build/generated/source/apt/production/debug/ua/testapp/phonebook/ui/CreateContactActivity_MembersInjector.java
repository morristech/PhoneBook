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
public final class CreateContactActivity_MembersInjector
    implements MembersInjector<CreateContactActivity> {
  private final Provider<ContactManager> mContactManagerProvider;

  private final Provider<BitmapUtil> mBitmapUtilProvider;

  private final Provider<FileUtil> mFileUtilProvider;

  private final Provider<DialogHelper> mDialogHelperProvider;

  private final Provider<ExternalStorageUtil> mExternalStorageUtilProvider;

  public CreateContactActivity_MembersInjector(
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

  public static MembersInjector<CreateContactActivity> create(
      Provider<ContactManager> mContactManagerProvider,
      Provider<BitmapUtil> mBitmapUtilProvider,
      Provider<FileUtil> mFileUtilProvider,
      Provider<DialogHelper> mDialogHelperProvider,
      Provider<ExternalStorageUtil> mExternalStorageUtilProvider) {
    return new CreateContactActivity_MembersInjector(
        mContactManagerProvider,
        mBitmapUtilProvider,
        mFileUtilProvider,
        mDialogHelperProvider,
        mExternalStorageUtilProvider);
  }

  @Override
  public void injectMembers(CreateContactActivity instance) {
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
      CreateContactActivity instance, Provider<ContactManager> mContactManagerProvider) {
    instance.mContactManager = mContactManagerProvider.get();
  }

  public static void injectMBitmapUtil(
      CreateContactActivity instance, Provider<BitmapUtil> mBitmapUtilProvider) {
    instance.mBitmapUtil = mBitmapUtilProvider.get();
  }

  public static void injectMFileUtil(
      CreateContactActivity instance, Provider<FileUtil> mFileUtilProvider) {
    instance.mFileUtil = mFileUtilProvider.get();
  }

  public static void injectMDialogHelper(
      CreateContactActivity instance, Provider<DialogHelper> mDialogHelperProvider) {
    instance.mDialogHelper = mDialogHelperProvider.get();
  }

  public static void injectMExternalStorageUtil(
      CreateContactActivity instance, Provider<ExternalStorageUtil> mExternalStorageUtilProvider) {
    instance.mExternalStorageUtil = mExternalStorageUtilProvider.get();
  }
}
