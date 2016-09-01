package ua.testapp.phonebook.repositories.phone;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PhoneRepository_Factory implements Factory<PhoneRepository> {
  private final Provider<PhoneDataSource> phoneRemoteDataSourceProvider;

  private final Provider<PhoneDataSource> phoneLocalDataSourceProvider;

  public PhoneRepository_Factory(
      Provider<PhoneDataSource> phoneRemoteDataSourceProvider,
      Provider<PhoneDataSource> phoneLocalDataSourceProvider) {
    assert phoneRemoteDataSourceProvider != null;
    this.phoneRemoteDataSourceProvider = phoneRemoteDataSourceProvider;
    assert phoneLocalDataSourceProvider != null;
    this.phoneLocalDataSourceProvider = phoneLocalDataSourceProvider;
  }

  @Override
  public PhoneRepository get() {
    return new PhoneRepository(
        phoneRemoteDataSourceProvider.get(), phoneLocalDataSourceProvider.get());
  }

  public static Factory<PhoneRepository> create(
      Provider<PhoneDataSource> phoneRemoteDataSourceProvider,
      Provider<PhoneDataSource> phoneLocalDataSourceProvider) {
    return new PhoneRepository_Factory(phoneRemoteDataSourceProvider, phoneLocalDataSourceProvider);
  }
}
