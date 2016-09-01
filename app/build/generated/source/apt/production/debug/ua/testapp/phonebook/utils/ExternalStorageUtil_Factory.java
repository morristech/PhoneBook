package ua.testapp.phonebook.utils;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public enum ExternalStorageUtil_Factory implements Factory<ExternalStorageUtil> {
  INSTANCE;

  @Override
  public ExternalStorageUtil get() {
    return new ExternalStorageUtil();
  }

  public static Factory<ExternalStorageUtil> create() {
    return INSTANCE;
  }
}
