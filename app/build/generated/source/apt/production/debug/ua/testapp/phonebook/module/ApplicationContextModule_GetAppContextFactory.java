package ua.testapp.phonebook.module;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApplicationContextModule_GetAppContextFactory implements Factory<Context> {
  private final ApplicationContextModule module;

  public ApplicationContextModule_GetAppContextFactory(ApplicationContextModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Context get() {
    return Preconditions.checkNotNull(
        module.getAppContext(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Context> create(ApplicationContextModule module) {
    return new ApplicationContextModule_GetAppContextFactory(module);
  }
}
