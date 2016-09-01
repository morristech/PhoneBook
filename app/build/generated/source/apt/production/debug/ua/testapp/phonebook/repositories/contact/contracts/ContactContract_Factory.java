package ua.testapp.phonebook.repositories.contact.contracts;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public enum ContactContract_Factory implements Factory<ContactContract> {
  INSTANCE;

  @Override
  public ContactContract get() {
    return new ContactContract();
  }

  public static Factory<ContactContract> create() {
    return INSTANCE;
  }
}
