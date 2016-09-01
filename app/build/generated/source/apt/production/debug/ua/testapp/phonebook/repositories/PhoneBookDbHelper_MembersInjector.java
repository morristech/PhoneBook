package ua.testapp.phonebook.repositories;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.repositories.contact.contracts.ContactContract;
import ua.testapp.phonebook.repositories.phone.contracts.PhoneContract;
import ua.testapp.phonebook.repositories.user.contracts.UserContract;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PhoneBookDbHelper_MembersInjector implements MembersInjector<PhoneBookDbHelper> {
  private final Provider<UserContract> mUserContractProvider;

  private final Provider<ContactContract> mContactContractProvider;

  private final Provider<PhoneContract> mPhoneContractProvider;

  public PhoneBookDbHelper_MembersInjector(
      Provider<UserContract> mUserContractProvider,
      Provider<ContactContract> mContactContractProvider,
      Provider<PhoneContract> mPhoneContractProvider) {
    assert mUserContractProvider != null;
    this.mUserContractProvider = mUserContractProvider;
    assert mContactContractProvider != null;
    this.mContactContractProvider = mContactContractProvider;
    assert mPhoneContractProvider != null;
    this.mPhoneContractProvider = mPhoneContractProvider;
  }

  public static MembersInjector<PhoneBookDbHelper> create(
      Provider<UserContract> mUserContractProvider,
      Provider<ContactContract> mContactContractProvider,
      Provider<PhoneContract> mPhoneContractProvider) {
    return new PhoneBookDbHelper_MembersInjector(
        mUserContractProvider, mContactContractProvider, mPhoneContractProvider);
  }

  @Override
  public void injectMembers(PhoneBookDbHelper instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mUserContract = mUserContractProvider.get();
    instance.mContactContract = mContactContractProvider.get();
    instance.mPhoneContract = mPhoneContractProvider.get();
  }

  public static void injectMUserContract(
      PhoneBookDbHelper instance, Provider<UserContract> mUserContractProvider) {
    instance.mUserContract = mUserContractProvider.get();
  }

  public static void injectMContactContract(
      PhoneBookDbHelper instance, Provider<ContactContract> mContactContractProvider) {
    instance.mContactContract = mContactContractProvider.get();
  }

  public static void injectMPhoneContract(
      PhoneBookDbHelper instance, Provider<PhoneContract> mPhoneContractProvider) {
    instance.mPhoneContract = mPhoneContractProvider.get();
  }
}
