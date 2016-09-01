package ua.testapp.phonebook.module;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.repositories.contact.ContactDataSource;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ContactRepositoryModule_ProvideContactRemoteDataSourceFactory
    implements Factory<ContactDataSource> {
  private final ContactRepositoryModule module;

  private final Provider<Context> contextProvider;

  public ContactRepositoryModule_ProvideContactRemoteDataSourceFactory(
      ContactRepositoryModule module, Provider<Context> contextProvider) {
    assert module != null;
    this.module = module;
    assert contextProvider != null;
    this.contextProvider = contextProvider;
  }

  @Override
  public ContactDataSource get() {
    return Preconditions.checkNotNull(
        module.provideContactRemoteDataSource(contextProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ContactDataSource> create(
      ContactRepositoryModule module, Provider<Context> contextProvider) {
    return new ContactRepositoryModule_ProvideContactRemoteDataSourceFactory(
        module, contextProvider);
  }
}
