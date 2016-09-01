package ua.testapp.phonebook.module;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.repositories.user.UserDataSource;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UserRepositoryModule_ProvideUserLocalDataSourceFactory
    implements Factory<UserDataSource> {
  private final UserRepositoryModule module;

  private final Provider<Context> contextProvider;

  public UserRepositoryModule_ProvideUserLocalDataSourceFactory(
      UserRepositoryModule module, Provider<Context> contextProvider) {
    assert module != null;
    this.module = module;
    assert contextProvider != null;
    this.contextProvider = contextProvider;
  }

  @Override
  public UserDataSource get() {
    return Preconditions.checkNotNull(
        module.provideUserLocalDataSource(contextProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<UserDataSource> create(
      UserRepositoryModule module, Provider<Context> contextProvider) {
    return new UserRepositoryModule_ProvideUserLocalDataSourceFactory(module, contextProvider);
  }
}
