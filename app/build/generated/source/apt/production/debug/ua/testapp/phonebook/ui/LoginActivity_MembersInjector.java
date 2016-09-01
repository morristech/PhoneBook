package ua.testapp.phonebook.ui;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.repositories.user.UserRepository;
import ua.testapp.phonebook.utils.DialogHelper;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class LoginActivity_MembersInjector implements MembersInjector<LoginActivity> {
  private final Provider<DialogHelper> mDialogHelperProvider;

  private final Provider<UserRepository> mUserRepositoryProvider;

  public LoginActivity_MembersInjector(
      Provider<DialogHelper> mDialogHelperProvider,
      Provider<UserRepository> mUserRepositoryProvider) {
    assert mDialogHelperProvider != null;
    this.mDialogHelperProvider = mDialogHelperProvider;
    assert mUserRepositoryProvider != null;
    this.mUserRepositoryProvider = mUserRepositoryProvider;
  }

  public static MembersInjector<LoginActivity> create(
      Provider<DialogHelper> mDialogHelperProvider,
      Provider<UserRepository> mUserRepositoryProvider) {
    return new LoginActivity_MembersInjector(mDialogHelperProvider, mUserRepositoryProvider);
  }

  @Override
  public void injectMembers(LoginActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mDialogHelper = mDialogHelperProvider.get();
    instance.mUserRepository = mUserRepositoryProvider.get();
  }

  public static void injectMDialogHelper(
      LoginActivity instance, Provider<DialogHelper> mDialogHelperProvider) {
    instance.mDialogHelper = mDialogHelperProvider.get();
  }

  public static void injectMUserRepository(
      LoginActivity instance, Provider<UserRepository> mUserRepositoryProvider) {
    instance.mUserRepository = mUserRepositoryProvider.get();
  }
}
