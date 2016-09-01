package ua.testapp.phonebook.component;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;
import ua.testapp.phonebook.loader.ContactsLoader;
import ua.testapp.phonebook.loader.ContactsLoader_MembersInjector;
import ua.testapp.phonebook.managers.ContactManager;
import ua.testapp.phonebook.managers.ContactManager_Factory;
import ua.testapp.phonebook.managers.ContactManager_MembersInjector;
import ua.testapp.phonebook.module.ApplicationContextModule;
import ua.testapp.phonebook.module.ApplicationContextModule_GetAppContextFactory;
import ua.testapp.phonebook.module.ContactRepositoryModule;
import ua.testapp.phonebook.module.ContactRepositoryModule_ProvideContactLocalDataSourceFactory;
import ua.testapp.phonebook.module.ContactRepositoryModule_ProvideContactRemoteDataSourceFactory;
import ua.testapp.phonebook.module.GsonModule;
import ua.testapp.phonebook.module.PhoneRepositoryModule;
import ua.testapp.phonebook.module.PhoneRepositoryModule_ProvidePhoneLocalDataSourceFactory;
import ua.testapp.phonebook.module.PhoneRepositoryModule_ProvidePhoneRemoteDataSourceFactory;
import ua.testapp.phonebook.module.UserRepositoryModule;
import ua.testapp.phonebook.module.UserRepositoryModule_ProvideUserLocalDataSourceFactory;
import ua.testapp.phonebook.module.UserRepositoryModule_ProvideUserRemoteDataSourceFactory;
import ua.testapp.phonebook.repositories.contact.ContactDataSource;
import ua.testapp.phonebook.repositories.contact.ContactRepository;
import ua.testapp.phonebook.repositories.contact.ContactRepository_Factory;
import ua.testapp.phonebook.repositories.contact.ContactRepository_MembersInjector;
import ua.testapp.phonebook.repositories.contact.local.ContactLocalDataSource;
import ua.testapp.phonebook.repositories.contact.remote.ContactRemoteDataSource;
import ua.testapp.phonebook.repositories.phone.PhoneDataSource;
import ua.testapp.phonebook.repositories.phone.PhoneRepository;
import ua.testapp.phonebook.repositories.phone.PhoneRepository_Factory;
import ua.testapp.phonebook.repositories.phone.local.PhoneLocalDataSource;
import ua.testapp.phonebook.repositories.phone.remote.PhoneRemoteDataSource;
import ua.testapp.phonebook.repositories.user.UserDataSource;
import ua.testapp.phonebook.repositories.user.UserRepository;
import ua.testapp.phonebook.repositories.user.UserRepository_Factory;
import ua.testapp.phonebook.repositories.user.local.UserLocalDataSource;
import ua.testapp.phonebook.repositories.user.remote.UserRemoteDataSource;
import ua.testapp.phonebook.ui.ContactDetailActivity;
import ua.testapp.phonebook.ui.CreateContactActivity;
import ua.testapp.phonebook.ui.CreateContactActivity_MembersInjector;
import ua.testapp.phonebook.ui.LoginActivity;
import ua.testapp.phonebook.ui.LoginActivity_MembersInjector;
import ua.testapp.phonebook.ui.MainActivity;
import ua.testapp.phonebook.ui.MainActivity_MembersInjector;
import ua.testapp.phonebook.ui.SplashActivity;
import ua.testapp.phonebook.ui.SplashActivity_MembersInjector;
import ua.testapp.phonebook.ui.adapters.ContactAdapter;
import ua.testapp.phonebook.ui.adapters.ContactAdapter_MembersInjector;
import ua.testapp.phonebook.utils.BitmapUtil;
import ua.testapp.phonebook.utils.BitmapUtil_Factory;
import ua.testapp.phonebook.utils.ContentUtil;
import ua.testapp.phonebook.utils.ContentUtil_Factory;
import ua.testapp.phonebook.utils.DialogHelper_Factory;
import ua.testapp.phonebook.utils.ExternalStorageUtil_Factory;
import ua.testapp.phonebook.utils.FileUtil;
import ua.testapp.phonebook.utils.FileUtil_Factory;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerGeneralComponent implements GeneralComponent {
  private Provider<Context> getAppContextProvider;

  private Provider<UserDataSource> provideUserRemoteDataSourceProvider;

  private Provider<UserDataSource> provideUserLocalDataSourceProvider;

  private Provider<UserRepository> userRepositoryProvider;

  private MembersInjector<SplashActivity> splashActivityMembersInjector;

  private MembersInjector<LoginActivity> loginActivityMembersInjector;

  private Provider<FileUtil> fileUtilProvider;

  private Provider<ContentUtil> contentUtilProvider;

  private Provider<BitmapUtil> bitmapUtilProvider;

  private MembersInjector<MainActivity> mainActivityMembersInjector;

  private MembersInjector<ContactRepository> contactRepositoryMembersInjector;

  private Provider<ContactDataSource> provideContactRemoteDataSourceProvider;

  private Provider<ContactDataSource> provideContactLocalDataSourceProvider;

  private Provider<ContactRepository> contactRepositoryProvider;

  private Provider<PhoneDataSource> providePhoneRemoteDataSourceProvider;

  private Provider<PhoneDataSource> providePhoneLocalDataSourceProvider;

  private Provider<PhoneRepository> phoneRepositoryProvider;

  private MembersInjector<ContactManager> contactManagerMembersInjector;

  private Provider<ContactManager> contactManagerProvider;

  private MembersInjector<CreateContactActivity> createContactActivityMembersInjector;

  private MembersInjector<ContactsLoader> contactsLoaderMembersInjector;

  private MembersInjector<ContactAdapter> contactAdapterMembersInjector;

  private DaggerGeneralComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.getAppContextProvider =
        ApplicationContextModule_GetAppContextFactory.create(builder.applicationContextModule);

    this.provideUserRemoteDataSourceProvider =
        ScopedProvider.create(
            UserRepositoryModule_ProvideUserRemoteDataSourceFactory.create(
                builder.userRepositoryModule, getAppContextProvider));

    this.provideUserLocalDataSourceProvider =
        ScopedProvider.create(
            UserRepositoryModule_ProvideUserLocalDataSourceFactory.create(
                builder.userRepositoryModule, getAppContextProvider));

    this.userRepositoryProvider =
        UserRepository_Factory.create(
            provideUserRemoteDataSourceProvider, provideUserLocalDataSourceProvider);

    this.splashActivityMembersInjector =
        SplashActivity_MembersInjector.create(userRepositoryProvider);

    this.loginActivityMembersInjector =
        LoginActivity_MembersInjector.create(DialogHelper_Factory.create(), userRepositoryProvider);

    this.fileUtilProvider = FileUtil_Factory.create(getAppContextProvider);

    this.contentUtilProvider = ContentUtil_Factory.create(getAppContextProvider);

    this.bitmapUtilProvider = BitmapUtil_Factory.create(contentUtilProvider);

    this.mainActivityMembersInjector =
        MainActivity_MembersInjector.create(
            fileUtilProvider,
            bitmapUtilProvider,
            ExternalStorageUtil_Factory.create(),
            DialogHelper_Factory.create());

    this.contactRepositoryMembersInjector =
        ContactRepository_MembersInjector.create(ExternalStorageUtil_Factory.create());

    this.provideContactRemoteDataSourceProvider =
        ScopedProvider.create(
            ContactRepositoryModule_ProvideContactRemoteDataSourceFactory.create(
                builder.contactRepositoryModule, getAppContextProvider));

    this.provideContactLocalDataSourceProvider =
        ScopedProvider.create(
            ContactRepositoryModule_ProvideContactLocalDataSourceFactory.create(
                builder.contactRepositoryModule, getAppContextProvider));

    this.contactRepositoryProvider =
        ContactRepository_Factory.create(
            contactRepositoryMembersInjector,
            provideContactRemoteDataSourceProvider,
            provideContactLocalDataSourceProvider);

    this.providePhoneRemoteDataSourceProvider =
        ScopedProvider.create(
            PhoneRepositoryModule_ProvidePhoneRemoteDataSourceFactory.create(
                builder.phoneRepositoryModule, getAppContextProvider));

    this.providePhoneLocalDataSourceProvider =
        ScopedProvider.create(
            PhoneRepositoryModule_ProvidePhoneLocalDataSourceFactory.create(
                builder.phoneRepositoryModule, getAppContextProvider));

    this.phoneRepositoryProvider =
        PhoneRepository_Factory.create(
            providePhoneRemoteDataSourceProvider, providePhoneLocalDataSourceProvider);

    this.contactManagerMembersInjector =
        ContactManager_MembersInjector.create(contactRepositoryProvider, phoneRepositoryProvider);

    this.contactManagerProvider = ContactManager_Factory.create(contactManagerMembersInjector);

    this.createContactActivityMembersInjector =
        CreateContactActivity_MembersInjector.create(
            contactManagerProvider,
            bitmapUtilProvider,
            fileUtilProvider,
            DialogHelper_Factory.create(),
            ExternalStorageUtil_Factory.create());

    this.contactsLoaderMembersInjector =
        ContactsLoader_MembersInjector.create(contactManagerProvider);

    this.contactAdapterMembersInjector = ContactAdapter_MembersInjector.create(fileUtilProvider);
  }

  @Override
  public void inject(SplashActivity splashActivity) {
    splashActivityMembersInjector.injectMembers(splashActivity);
  }

  @Override
  public void inject(LoginActivity loginActivity) {
    loginActivityMembersInjector.injectMembers(loginActivity);
  }

  @Override
  public void inject(MainActivity mainActivity) {
    mainActivityMembersInjector.injectMembers(mainActivity);
  }

  @Override
  public void inject(CreateContactActivity createContactActivity) {
    createContactActivityMembersInjector.injectMembers(createContactActivity);
  }

  @Override
  public void inject(ContactDetailActivity contactDetailActivity) {
    MembersInjectors.<ContactDetailActivity>noOp().injectMembers(contactDetailActivity);
  }

  @Override
  public void inject(UserLocalDataSource credentialLocalDataSource) {
    MembersInjectors.<UserLocalDataSource>noOp().injectMembers(credentialLocalDataSource);
  }

  @Override
  public void inject(UserRemoteDataSource credentialRemoteDataSources) {
    MembersInjectors.<UserRemoteDataSource>noOp().injectMembers(credentialRemoteDataSources);
  }

  @Override
  public void inject(ContactLocalDataSource contactLocalDataSource) {
    MembersInjectors.<ContactLocalDataSource>noOp().injectMembers(contactLocalDataSource);
  }

  @Override
  public void inject(ContactRemoteDataSource contactRemoteDataSource) {
    MembersInjectors.<ContactRemoteDataSource>noOp().injectMembers(contactRemoteDataSource);
  }

  @Override
  public void inject(PhoneLocalDataSource phoneLocalDataSource) {
    MembersInjectors.<PhoneLocalDataSource>noOp().injectMembers(phoneLocalDataSource);
  }

  @Override
  public void inject(PhoneRemoteDataSource phoneRemoteDataSource) {
    MembersInjectors.<PhoneRemoteDataSource>noOp().injectMembers(phoneRemoteDataSource);
  }

  @Override
  public void inject(ContactsLoader contactsLoader) {
    contactsLoaderMembersInjector.injectMembers(contactsLoader);
  }

  @Override
  public void inject(ContactAdapter contactAdapter) {
    contactAdapterMembersInjector.injectMembers(contactAdapter);
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private UserRepositoryModule userRepositoryModule;

    private ContactRepositoryModule contactRepositoryModule;

    private PhoneRepositoryModule phoneRepositoryModule;

    private Builder() {}

    public GeneralComponent build() {
      if (applicationContextModule == null) {
        throw new IllegalStateException(
            ApplicationContextModule.class.getCanonicalName() + " must be set");
      }
      if (userRepositoryModule == null) {
        this.userRepositoryModule = new UserRepositoryModule();
      }
      if (contactRepositoryModule == null) {
        this.contactRepositoryModule = new ContactRepositoryModule();
      }
      if (phoneRepositoryModule == null) {
        this.phoneRepositoryModule = new PhoneRepositoryModule();
      }
      return new DaggerGeneralComponent(this);
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder gsonModule(GsonModule gsonModule) {
      Preconditions.checkNotNull(gsonModule);
      return this;
    }

    public Builder userRepositoryModule(UserRepositoryModule userRepositoryModule) {
      this.userRepositoryModule = Preconditions.checkNotNull(userRepositoryModule);
      return this;
    }

    public Builder contactRepositoryModule(ContactRepositoryModule contactRepositoryModule) {
      this.contactRepositoryModule = Preconditions.checkNotNull(contactRepositoryModule);
      return this;
    }

    public Builder phoneRepositoryModule(PhoneRepositoryModule phoneRepositoryModule) {
      this.phoneRepositoryModule = Preconditions.checkNotNull(phoneRepositoryModule);
      return this;
    }
  }
}
