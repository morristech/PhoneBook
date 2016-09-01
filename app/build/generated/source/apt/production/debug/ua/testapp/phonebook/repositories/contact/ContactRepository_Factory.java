package ua.testapp.phonebook.repositories.contact;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ContactRepository_Factory implements Factory<ContactRepository> {
  private final MembersInjector<ContactRepository> contactRepositoryMembersInjector;

  private final Provider<ContactDataSource> contactRemoteDataSourceProvider;

  private final Provider<ContactDataSource> contactLocalDataSourceProvider;

  public ContactRepository_Factory(
      MembersInjector<ContactRepository> contactRepositoryMembersInjector,
      Provider<ContactDataSource> contactRemoteDataSourceProvider,
      Provider<ContactDataSource> contactLocalDataSourceProvider) {
    assert contactRepositoryMembersInjector != null;
    this.contactRepositoryMembersInjector = contactRepositoryMembersInjector;
    assert contactRemoteDataSourceProvider != null;
    this.contactRemoteDataSourceProvider = contactRemoteDataSourceProvider;
    assert contactLocalDataSourceProvider != null;
    this.contactLocalDataSourceProvider = contactLocalDataSourceProvider;
  }

  @Override
  public ContactRepository get() {
    return MembersInjectors.injectMembers(
        contactRepositoryMembersInjector,
        new ContactRepository(
            contactRemoteDataSourceProvider.get(), contactLocalDataSourceProvider.get()));
  }

  public static Factory<ContactRepository> create(
      MembersInjector<ContactRepository> contactRepositoryMembersInjector,
      Provider<ContactDataSource> contactRemoteDataSourceProvider,
      Provider<ContactDataSource> contactLocalDataSourceProvider) {
    return new ContactRepository_Factory(
        contactRepositoryMembersInjector,
        contactRemoteDataSourceProvider,
        contactLocalDataSourceProvider);
  }
}
