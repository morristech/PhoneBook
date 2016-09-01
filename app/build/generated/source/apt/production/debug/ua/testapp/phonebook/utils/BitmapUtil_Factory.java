package ua.testapp.phonebook.utils;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class BitmapUtil_Factory implements Factory<BitmapUtil> {
  private final Provider<ContentUtil> contentUtilProvider;

  public BitmapUtil_Factory(Provider<ContentUtil> contentUtilProvider) {
    assert contentUtilProvider != null;
    this.contentUtilProvider = contentUtilProvider;
  }

  @Override
  public BitmapUtil get() {
    return new BitmapUtil(contentUtilProvider.get());
  }

  public static Factory<BitmapUtil> create(Provider<ContentUtil> contentUtilProvider) {
    return new BitmapUtil_Factory(contentUtilProvider);
  }
}
