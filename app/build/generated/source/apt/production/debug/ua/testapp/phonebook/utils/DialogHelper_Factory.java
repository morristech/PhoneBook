package ua.testapp.phonebook.utils;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public enum DialogHelper_Factory implements Factory<DialogHelper> {
  INSTANCE;

  @Override
  public DialogHelper get() {
    return new DialogHelper();
  }

  public static Factory<DialogHelper> create() {
    return INSTANCE;
  }
}
