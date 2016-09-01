package ua.testapp.phonebook.utils;

import android.content.Context;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ContentUtil_Factory implements Factory<ContentUtil> {
  private final Provider<Context> contextProvider;

  public ContentUtil_Factory(Provider<Context> contextProvider) {
    assert contextProvider != null;
    this.contextProvider = contextProvider;
  }

  @Override
  public ContentUtil get() {
    return new ContentUtil(contextProvider.get());
  }

  public static Factory<ContentUtil> create(Provider<Context> contextProvider) {
    return new ContentUtil_Factory(contextProvider);
  }
}
