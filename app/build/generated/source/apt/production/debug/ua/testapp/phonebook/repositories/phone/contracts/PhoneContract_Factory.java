package ua.testapp.phonebook.repositories.phone.contracts;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public enum PhoneContract_Factory implements Factory<PhoneContract> {
  INSTANCE;

  @Override
  public PhoneContract get() {
    return new PhoneContract();
  }

  public static Factory<PhoneContract> create() {
    return INSTANCE;
  }
}
