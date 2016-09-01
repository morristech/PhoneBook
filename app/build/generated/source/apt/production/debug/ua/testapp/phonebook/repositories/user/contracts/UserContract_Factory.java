package ua.testapp.phonebook.repositories.user.contracts;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public enum UserContract_Factory implements Factory<UserContract> {
  INSTANCE;

  @Override
  public UserContract get() {
    return new UserContract();
  }

  public static Factory<UserContract> create() {
    return INSTANCE;
  }
}
