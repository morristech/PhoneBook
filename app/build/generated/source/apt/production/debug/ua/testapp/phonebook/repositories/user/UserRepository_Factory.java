package ua.testapp.phonebook.repositories.user;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UserRepository_Factory implements Factory<UserRepository> {
  private final Provider<UserDataSource> userRemoteDataSourceProvider;

  private final Provider<UserDataSource> userLocalDataSourceProvider;

  public UserRepository_Factory(
      Provider<UserDataSource> userRemoteDataSourceProvider,
      Provider<UserDataSource> userLocalDataSourceProvider) {
    assert userRemoteDataSourceProvider != null;
    this.userRemoteDataSourceProvider = userRemoteDataSourceProvider;
    assert userLocalDataSourceProvider != null;
    this.userLocalDataSourceProvider = userLocalDataSourceProvider;
  }

  @Override
  public UserRepository get() {
    return new UserRepository(
        userRemoteDataSourceProvider.get(), userLocalDataSourceProvider.get());
  }

  public static Factory<UserRepository> create(
      Provider<UserDataSource> userRemoteDataSourceProvider,
      Provider<UserDataSource> userLocalDataSourceProvider) {
    return new UserRepository_Factory(userRemoteDataSourceProvider, userLocalDataSourceProvider);
  }
}
