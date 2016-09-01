package ua.testapp.phonebook.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.R;
import ua.testapp.phonebook.constants.IntentData;
import ua.testapp.phonebook.constants.LoaderIds;
import ua.testapp.phonebook.interfaces.ContactActionListener;
import ua.testapp.phonebook.interfaces.TaskCompleteListener;
import ua.testapp.phonebook.loader.ContactsLoader;
import ua.testapp.phonebook.model.Contact;
import ua.testapp.phonebook.ui.adapters.ContactAdapter;
import ua.testapp.phonebook.ui.decorators.SpaceItemDecoration;
import ua.testapp.phonebook.utils.BitmapUtil;
import ua.testapp.phonebook.utils.DialogHelper;
import ua.testapp.phonebook.utils.ExternalStorageUtil;
import ua.testapp.phonebook.utils.FileUtil;
import ua.testapp.phonebook.utils.ImageLoaderHelper;
import ua.testapp.phonebook.utils.PermissionUtil;
import ua.testapp.phonebook.utils.PreferenceHelper;
import ua.testapp.phonebook.utils.UIHelper;

public class MainActivity extends AbstractBaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fab_add_contact)
    FloatingActionButton fabAddContact;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

   @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rv_contacts)
    RecyclerView rvContacts;

    @BindView(R.id.progress_wheel)
    ProgressWheel progressBar;

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    @BindView(R.id.tv_empty_text)
    TextView tvEmptyText;

    private View viewHeader;
    private ImageView ivAvatar;
    private TextView tvUserData;
    private TextView tvMyPhoneNumber;

    @Inject
    FileUtil mFileUtil;

    @Inject
    BitmapUtil mBitmapUtil;

    @Inject
    ExternalStorageUtil mExternalStorageUtil;

    @Inject
    DialogHelper mDialogHelper;

    private boolean mIsGrantedPermission;
    private ContactAdapter mContactAdapter;
    private Menu mMenu;
    private boolean mIsSelectedModOn;
    private List<Contact> mSelectedContacts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PhoneBookApplication) getApplicationContext()).getGeneralComponent().inject(this);

        mIsGrantedPermission = PermissionUtil.requestCheckPermissions(this);

        initLeftMenuHeaderViews();
        actionUpdateUserAvatar();

        mDialogHelper.setContext(this);
    }

    private void initLeftMenuHeaderViews() {
        viewHeader = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ivAvatar = (ImageView) viewHeader.findViewById(R.id.iv_avatar);
        tvMyPhoneNumber = (TextView) viewHeader.findViewById(R.id.tv_my_phone_number);
        tvUserData = (TextView) viewHeader.findViewById(R.id.tv_user_data);
    }

    @Override
    protected void initContent() {
        /** init title */
        setTitleToolBar(getString(R.string.app_name));

        /** init start activity views */
        flContainer.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        tvEmptyText.setVisibility(View.VISIBLE);

        /** init left menu */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        /** init contact adapter */
        mIsSelectedModOn = false;
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        mContactAdapter = new ContactAdapter(this);
        rvContacts.setAdapter(mContactAdapter);
        rvContacts.addItemDecoration(new SpaceItemDecoration());

        mContactAdapter.setOnItemLongClickListener(new TaskCompleteListener<Boolean>() {
            @Override
            public void onTaskComplete(Boolean result) {
                if (!result) {
                    actionClearSelectedPhotos();
                }
            }

            @Override
            public void onFail() {

            }
        });

        mContactAdapter.setOnContactActionListener(new ContactActionListener<Contact>() {
            @Override
            public void onAddContact(Contact result) {
                actionAddPhotoInList(result);
            }

            @Override
            public void onRemoveContact(Contact result) {
                actionRemoveContactInList(result);
            }
        });

        /** swipe to refresh */
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDark);

        swipeRefreshLayout.setOnRefreshListener(() -> swipeRefreshLayout.setRefreshing(false)); //TODO EventBus.getDefault().post(LoadContactsEvent.INSTANCE)

        /** load contacts */
        getSupportLoaderManager().initLoader(LoaderIds.LOADER_ID__CONTACTS, null, new LoaderManager.LoaderCallbacks<List<Contact>>() {

            @Override
            public Loader<List<Contact>> onCreateLoader(int id, Bundle args) {
                return new ContactsLoader(MainActivity.this);
            }

            @Override
            public void onLoadFinished(Loader<List<Contact>> loader, List<Contact> contactList) {
                actionAfterLoadContacts(contactList);
            }

            @Override
            public void onLoaderReset(Loader<List<Contact>> loader) {
            }
        });
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        actionInitUserData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case IntentData.REQUEST_CODE_PICTURE:
                if (resultCode == RESULT_OK) {
                    actionUpdateUserAvatar();
                }

                break;
            case IntentData.REQUEST_CODE_GALLERY:
                if (resultCode == RESULT_OK) {
                    actionSaveImageAndUpdateAvatar(data);
                }
                break;

            default:

        }
    }

    @OnClick(R.id.fab_add_contact)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_contact:
                actionAddContact();
                break;
            default:
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mIsGrantedPermission = PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {

        } else if (id == R.id.action_remove) {

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            actionMakePhotoCamera();
        } else if (id == R.id.nav_gallery) {
            actionOpenGallery();
        } else if (id == R.id.nav_settings) {
            actionOpenSettings();
        } else if (id == R.id.nav_sync) {
            actionSyncContacts();
        } else if (id == R.id.nav_exit) {
            actionExitApp();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void actionAfterLoadContacts(List<Contact> contactList) {
        mContactAdapter.setContacts(contactList);

        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);

        if (!contactList.isEmpty()) {
            tvEmptyText.setVisibility(View.GONE);
            flContainer.setVisibility(View.VISIBLE);
        } else {
            tvEmptyText.setVisibility(View.VISIBLE);
            flContainer.setVisibility(View.GONE);
        }
    }

    private void actionSaveImageAndUpdateAvatar(Intent data) {
        Uri selectedImageUri = data.getData();
        if (selectedImageUri != null) {
            mExternalStorageUtil.saveImageFile(PreferenceHelper.getUserLogin(), mBitmapUtil.createOriginalBitmapFromPath(selectedImageUri, selectedImageUri),
                    new TaskCompleteListener<Boolean>() {

                        @Override
                        public void onTaskComplete(Boolean result) {
                            if (result) {
                                actionUpdateUserAvatar();
                            } else {
                                ivAvatar.setImageResource(R.drawable.ic_avatar_def);
                                UIHelper.showSnackbar(navigationView, getString(R.string.error_photo_not_save));
                            }
                        }

                        @Override
                        public void onFail() {
                            ivAvatar.setImageResource(R.drawable.ic_avatar_def);
                            UIHelper.showSnackbar(navigationView, getString(R.string.error_photo_not_save));
                        }
                    });
        }
    }

    private void actionUpdateUserAvatar() {
        File fileAvatar = mFileUtil.getFileByFileName(PreferenceHelper.getUserLogin());
        if (fileAvatar.length() > 0) {
            ImageLoaderHelper.loadAvatar(this, fileAvatar, ivAvatar);
        } else {
            ivAvatar.setImageResource(R.drawable.ic_avatar_def);
        }
    }

    private void actionSyncContacts() {
        UIHelper.showSnackbar(drawer, getString(R.string.error_sync_contacts));
    }

    private void actionMakePhotoCamera() {
        if (!mIsGrantedPermission) {
            mIsGrantedPermission = PermissionUtil.requestCameraAndExternalStoragePermissions(this);
        } else {
            File photo = null;
            try {
                photo = mFileUtil.createImageAvatarFile();
            } catch (Exception e) {
            }

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (photo != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
            }
            startActivityForResult(takePictureIntent, IntentData.REQUEST_CODE_PICTURE);
        }
    }

    private void actionOpenGallery() {
        if (!mIsGrantedPermission) {
            mIsGrantedPermission = PermissionUtil.requestCameraAndExternalStoragePermissions(this);
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), IntentData.REQUEST_CODE_GALLERY);
        }
    }

    private void actionOpenSettings() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    private void actionInitUserData() {
        String userData = "";
        tvUserData.setText("");
        if (!TextUtils.isEmpty(PreferenceHelper.getUserSurname())) {
            userData += PreferenceHelper.getUserSurname().concat(" ");
        }
        userData += PreferenceHelper.getUserName();
        if (!TextUtils.isEmpty(userData)) {
            tvUserData.setText(userData);
        } else {
            tvUserData.setText("Verbitskiy Alexey");
        }

        if (!TextUtils.isEmpty(PreferenceHelper.getUserPhoneNumber())) {
            tvMyPhoneNumber.setText("+38 ".concat(PreferenceHelper.getUserPhoneNumber()));
        } else {
            tvMyPhoneNumber.setText("+38 (095) 477-66-20");
        }
    }

    private void actionAddPhotoInList(Contact contact) {
        mSelectedContacts.add(contact);
        if (mSelectedContacts.size() == 1) {

            MenuItem itemSearch = mMenu.findItem(R.id.action_search);
            itemSearch.setVisible(false);

            MenuItem itemEdit = mMenu.findItem(R.id.action_remove);
            itemEdit.setVisible(true);

            mIsSelectedModOn = true;
            mContactAdapter.setIsMultiSelectMode(mIsSelectedModOn);
        }
    }

    private void actionRemoveContactInList(Contact contact) {
        mSelectedContacts.remove(contact);
        if (mSelectedContacts.size() == 0) {
            MenuItem itemSearch = mMenu.findItem(R.id.action_search);
            itemSearch.setVisible(true);

            MenuItem itemEdit = mMenu.findItem(R.id.action_remove);
            itemEdit.setVisible(false);

            mIsSelectedModOn = false;
            mContactAdapter.setIsMultiSelectMode(mIsSelectedModOn);
        }
    }

    private void actionClearSelectedPhotos() {
        mSelectedContacts.clear();

        MenuItem itemSearch = mMenu.findItem(R.id.action_search);
        itemSearch.setVisible(true);

        MenuItem itemRemove = mMenu.findItem(R.id.action_remove);
        itemRemove.setVisible(false);

        mContactAdapter.clearSelectedContacts();

        mIsSelectedModOn = false;
        mContactAdapter.setIsMultiSelectMode(mIsSelectedModOn);

    }

    private void actionAddContact() {
        Intent intent = new Intent(MainActivity.this, CreateContactActivity.class);
        startActivity(intent);
    }

    private void actionExitApp() {
        //clear shared preference data
        PreferenceHelper.clearInfo();

        // go to login
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
