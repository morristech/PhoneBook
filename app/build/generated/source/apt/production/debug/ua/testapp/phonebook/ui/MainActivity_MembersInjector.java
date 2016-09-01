package ua.testapp.phonebook.ui;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.utils.BitmapUtil;
import ua.testapp.phonebook.utils.DialogHelper;
import ua.testapp.phonebook.utils.ExternalStorageUtil;
import ua.testapp.phonebook.utils.FileUtil;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<FileUtil> mFileUtilProvider;

  private final Provider<BitmapUtil> mBitmapUtilProvider;

  private final Provider<ExternalStorageUtil> mExternalStorageUtilProvider;

  private final Provider<DialogHelper> mDialogHelperProvider;

  public MainActivity_MembersInjector(
      Provider<FileUtil> mFileUtilProvider,
      Provider<BitmapUtil> mBitmapUtilProvider,
      Provider<ExternalStorageUtil> mExternalStorageUtilProvider,
      Provider<DialogHelper> mDialogHelperProvider) {
    assert mFileUtilProvider != null;
    this.mFileUtilProvider = mFileUtilProvider;
    assert mBitmapUtilProvider != null;
    this.mBitmapUtilProvider = mBitmapUtilProvider;
    assert mExternalStorageUtilProvider != null;
    this.mExternalStorageUtilProvider = mExternalStorageUtilProvider;
    assert mDialogHelperProvider != null;
    this.mDialogHelperProvider = mDialogHelperProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<FileUtil> mFileUtilProvider,
      Provider<BitmapUtil> mBitmapUtilProvider,
      Provider<ExternalStorageUtil> mExternalStorageUtilProvider,
      Provider<DialogHelper> mDialogHelperProvider) {
    return new MainActivity_MembersInjector(
        mFileUtilProvider,
        mBitmapUtilProvider,
        mExternalStorageUtilProvider,
        mDialogHelperProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mFileUtil = mFileUtilProvider.get();
    instance.mBitmapUtil = mBitmapUtilProvider.get();
    instance.mExternalStorageUtil = mExternalStorageUtilProvider.get();
    instance.mDialogHelper = mDialogHelperProvider.get();
  }

  public static void injectMFileUtil(MainActivity instance, Provider<FileUtil> mFileUtilProvider) {
    instance.mFileUtil = mFileUtilProvider.get();
  }

  public static void injectMBitmapUtil(
      MainActivity instance, Provider<BitmapUtil> mBitmapUtilProvider) {
    instance.mBitmapUtil = mBitmapUtilProvider.get();
  }

  public static void injectMExternalStorageUtil(
      MainActivity instance, Provider<ExternalStorageUtil> mExternalStorageUtilProvider) {
    instance.mExternalStorageUtil = mExternalStorageUtilProvider.get();
  }

  public static void injectMDialogHelper(
      MainActivity instance, Provider<DialogHelper> mDialogHelperProvider) {
    instance.mDialogHelper = mDialogHelperProvider.get();
  }
}
