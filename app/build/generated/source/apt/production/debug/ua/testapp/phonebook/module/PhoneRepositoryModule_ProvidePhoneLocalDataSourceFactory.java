package ua.testapp.phonebook.module;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.repositories.phone.PhoneDataSource;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PhoneRepositoryModule_ProvidePhoneLocalDataSourceFactory
    implements Factory<PhoneDataSource> {
  private final PhoneRepositoryModule module;

  private final Provider<Context> contextProvider;

  public PhoneRepositoryModule_ProvidePhoneLocalDataSourceFactory(
      PhoneRepositoryModule module, Provider<Context> contextProvider) {
    assert module != null;
    this.module = module;
    assert contextProvider != null;
    this.contextProvider = contextProvider;
  }

  @Override
  public PhoneDataSource get() {
    return Preconditions.checkNotNull(
        module.providePhoneLocalDataSource(contextProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<PhoneDataSource> create(
      PhoneRepositoryModule module, Provider<Context> contextProvider) {
    return new PhoneRepositoryModule_ProvidePhoneLocalDataSourceFactory(module, contextProvider);
  }
}
