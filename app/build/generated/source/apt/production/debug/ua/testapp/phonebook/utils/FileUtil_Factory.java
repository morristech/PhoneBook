package ua.testapp.phonebook.utils;

import android.content.Context;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class FileUtil_Factory implements Factory<FileUtil> {
  private final Provider<Context> contextProvider;

  public FileUtil_Factory(Provider<Context> contextProvider) {
    assert contextProvider != null;
    this.contextProvider = contextProvider;
  }

  @Override
  public FileUtil get() {
    return new FileUtil(contextProvider.get());
  }

  public static Factory<FileUtil> create(Provider<Context> contextProvider) {
    return new FileUtil_Factory(contextProvider);
  }
}
