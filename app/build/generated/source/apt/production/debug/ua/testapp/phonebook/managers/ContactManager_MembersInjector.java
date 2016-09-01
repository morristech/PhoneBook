package ua.testapp.phonebook.managers;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.repositories.contact.ContactRepository;
import ua.testapp.phonebook.repositories.phone.PhoneRepository;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ContactManager_MembersInjector implements MembersInjector<ContactManager> {
  private final Provider<ContactRepository> mContactRepositoryProvider;

  private final Provider<PhoneRepository> mPhoneRepositoryProvider;

  public ContactManager_MembersInjector(
      Provider<ContactRepository> mContactRepositoryProvider,
      Provider<PhoneRepository> mPhoneRepositoryProvider) {
    assert mContactRepositoryProvider != null;
    this.mContactRepositoryProvider = mContactRepositoryProvider;
    assert mPhoneRepositoryProvider != null;
    this.mPhoneRepositoryProvider = mPhoneRepositoryProvider;
  }

  public static MembersInjector<ContactManager> create(
      Provider<ContactRepository> mContactRepositoryProvider,
      Provider<PhoneRepository> mPhoneRepositoryProvider) {
    return new ContactManager_MembersInjector(mContactRepositoryProvider, mPhoneRepositoryProvider);
  }

  @Override
  public void injectMembers(ContactManager instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mContactRepository = mContactRepositoryProvider.get();
    instance.mPhoneRepository = mPhoneRepositoryProvider.get();
  }

  public static void injectMContactRepository(
      ContactManager instance, Provider<ContactRepository> mContactRepositoryProvider) {
    instance.mContactRepository = mContactRepositoryProvider.get();
  }

  public static void injectMPhoneRepository(
      ContactManager instance, Provider<PhoneRepository> mPhoneRepositoryProvider) {
    instance.mPhoneRepository = mPhoneRepositoryProvider.get();
  }
}
