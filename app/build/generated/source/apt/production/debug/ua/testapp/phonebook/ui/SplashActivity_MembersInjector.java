package ua.testapp.phonebook.ui;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.repositories.user.UserRepository;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SplashActivity_MembersInjector implements MembersInjector<SplashActivity> {
  private final Provider<UserRepository> mUserRepositoryProvider;

  public SplashActivity_MembersInjector(Provider<UserRepository> mUserRepositoryProvider) {
    assert mUserRepositoryProvider != null;
    this.mUserRepositoryProvider = mUserRepositoryProvider;
  }

  public static MembersInjector<SplashActivity> create(
      Provider<UserRepository> mUserRepositoryProvider) {
    return new SplashActivity_MembersInjector(mUserRepositoryProvider);
  }

  @Override
  public void injectMembers(SplashActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mUserRepository = mUserRepositoryProvider.get();
  }

  public static void injectMUserRepository(
      SplashActivity instance, Provider<UserRepository> mUserRepositoryProvider) {
    instance.mUserRepository = mUserRepositoryProvider.get();
  }
}
