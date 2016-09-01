package ua.testapp.phonebook.managers;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ContactManager_Factory implements Factory<ContactManager> {
  private final MembersInjector<ContactManager> contactManagerMembersInjector;

  public ContactManager_Factory(MembersInjector<ContactManager> contactManagerMembersInjector) {
    assert contactManagerMembersInjector != null;
    this.contactManagerMembersInjector = contactManagerMembersInjector;
  }

  @Override
  public ContactManager get() {
    return MembersInjectors.injectMembers(contactManagerMembersInjector, new ContactManager());
  }

  public static Factory<ContactManager> create(
      MembersInjector<ContactManager> contactManagerMembersInjector) {
    return new ContactManager_Factory(contactManagerMembersInjector);
  }
}
