package ua.testapp.phonebook.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.sql.Array;
import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.R;
import ua.testapp.phonebook.constants.AppConfig;
import ua.testapp.phonebook.constants.Extras;
import ua.testapp.phonebook.constants.IntentData;
import ua.testapp.phonebook.interfaces.TaskCompleteListener;
import ua.testapp.phonebook.managers.ContactManager;
import ua.testapp.phonebook.model.Contact;
import ua.testapp.phonebook.model.Phone;
import ua.testapp.phonebook.ui.customView.EditTextSimpleError;
import ua.testapp.phonebook.utils.BitmapUtil;
import ua.testapp.phonebook.utils.DialogHelper;
import ua.testapp.phonebook.utils.ExternalStorageUtil;
import ua.testapp.phonebook.utils.FileUtil;
import ua.testapp.phonebook.utils.ImageLoaderHelper;
import ua.testapp.phonebook.utils.PermissionUtil;
import ua.testapp.phonebook.utils.PreferenceHelper;
import ua.testapp.phonebook.utils.SimpleTextWatcher;
import ua.testapp.phonebook.utils.UIHelper;
import ua.testapp.phonebook.utils.UUIDUtil;
import ua.testapp.phonebook.utils.UsPhoneNumberFormatter;

/**
 * Created by alexey on 26.08.16.
 */
public class CreateContactActivity extends AbstractBaseActivity {

    @BindView(R.id.root_view)
    View rootView;

    @BindView(R.id.et_name)
    EditTextSimpleError etName;

    @BindView(R.id.et_surname)
    EditText etSurname;

    @BindView(R.id.et_phone_number)
    EditTextSimpleError etPhoneNumber;

    @BindView(R.id.et_position)
    EditText etPosition;

    @BindView(R.id.et_mail)
    EditText etEmail;

    @BindView(R.id.et_contact_link)
    EditText etContactLink;

    @BindView(R.id.iv_photo)
    ImageView ivPhoto;

    @Inject
    ContactManager mContactManager;

    @Inject
    BitmapUtil mBitmapUtil;

    @Inject
    FileUtil mFileUtil;

    @Inject
    DialogHelper mDialogHelper;

    @Inject
    ExternalStorageUtil mExternalStorageUtil;

    private boolean mIsGrantedPermission;
    private Bitmap mImageBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PhoneBookApplication) getApplicationContext()).getGeneralComponent().inject(this);
        mIsGrantedPermission = PermissionUtil.requestCheckPermissions(this);
        mDialogHelper.setContext(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_create_contact;
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initContent() {
        UsPhoneNumberFormatter addLineNumberFormatter = new UsPhoneNumberFormatter(new WeakReference<>(etPhoneNumber), etPhoneNumber);
        etPhoneNumber.addTextChangedListener(addLineNumberFormatter);

        etName.addTextChangedListener(new SimpleTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.length() > 0) {
                    etName.setError(null);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentData.REQUEST_CODE_PICTURE:
                if (resultCode == RESULT_OK) {
                    actionSetPhotoWithCamera(data);
                }

                break;
            case IntentData.REQUEST_CODE_GALLERY:
                if (resultCode == RESULT_OK) {
                    actionSetPhotoWithGallery(data);
                }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_contact_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_camera) {
            actionOpenCamera();
        } else if (id == R.id.action_gallery) {
            actionOpenGallery();
        } else if (id == R.id.action_done) {
            actionSaveContact();
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionSetPhotoWithGallery(Intent data) {
        Uri selectedImageUri = data.getData();
        mImageBitmap = mBitmapUtil.createOriginalBitmapFromPath(selectedImageUri, selectedImageUri);
        if (selectedImageUri != null && mImageBitmap != null) {
            ivPhoto.setVisibility(View.VISIBLE);
            ivPhoto.setImageBitmap(mImageBitmap);
        }
    }

    private void actionSetPhotoWithCamera(Intent data) {
        Bundle extras = null;
        if (data != null) {
            extras = data.getExtras();
        }

        if (extras != null && !extras.isEmpty() && extras.get(Extras.DATA) != null) {
            mImageBitmap = (Bitmap) extras.get("data");
        } else {
            mImageBitmap = mBitmapUtil.createBitmapFromPath(mFileUtil.getLastImagePath());
        }

        if (mImageBitmap != null) {
            ivPhoto.setVisibility(View.VISIBLE);
            ivPhoto.setImageBitmap(mImageBitmap);
        }
    }

    private void actionOpenCamera() {
        if (!mIsGrantedPermission) {
            mIsGrantedPermission = PermissionUtil.requestCameraAndExternalStoragePermissions(this);
        } else {
            File photo = null;
            try {
                photo = mFileUtil.createImageFile();
            } catch(Exception e) {}
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

    private boolean checkContactFields() {
        if (!TextUtils.isEmpty(etName.getText().toString()) &&
                !TextUtils.isEmpty(etPhoneNumber.getText().toString()) &&
                etPhoneNumber.getText().toString().length() == AppConfig.PHONE_NUMBER_LENGTH) {
            return true;
        } else {
            if (etPhoneNumber.getText().toString().length() != AppConfig.PHONE_NUMBER_LENGTH) {
                etPhoneNumber.setError("");
            }

            if (TextUtils.isEmpty(etName.getText().toString())) {
                etName.setError("");
            }

            UIHelper.showSnackbar(rootView, getString(R.string.error_create_contact));
            return false;
        }
    }

    private void actionSaveContact() {

        UIHelper.hideKeyboard(this);

        if (checkContactFields()) {
            showProcessDialog();

            Contact newContact = new Contact();
            newContact.setId(UUIDUtil.getRandomUUID().toString());
            if (mImageBitmap != null) {
                mExternalStorageUtil.saveImageFile(newContact.getId(), mImageBitmap, new TaskCompleteListener<Boolean>() {
                    @Override
                    public void onTaskComplete(Boolean result) {
                        if (result) {
                            initAndSaveContact(newContact);
                        } else {
                            showErrorDialog(getString(R.string.error_dialog_contact_save_fail));
                        }
                    }

                    @Override
                    public void onFail() {
                        showErrorDialog(getString(R.string.error_dialog_contact_save_fail));
                    }
                });
            } else {
                initAndSaveContact(newContact);
            }
        }
    }

    private void initAndSaveContact(Contact contact){
        /** init */
        if (!TextUtils.isEmpty(etName.getText().toString())) {
            contact.setName(etName.getText().toString());
        }

        if (!TextUtils.isEmpty(etSurname.getText().toString())) {
            contact.setSurname(etSurname.getText().toString());
        }

        if (!TextUtils.isEmpty(etPosition.getText().toString())) {
            contact.setPosition(etPosition.getText().toString());
        }

        if (!TextUtils.isEmpty(etEmail.getText().toString())) {
            contact.setEmail(etEmail.getText().toString());
        }

        if (!TextUtils.isEmpty(etContactLink.getText().toString())) {
            contact.setContactLink(etContactLink.getText().toString());
        }

        if (mImageBitmap != null) {
            contact.setPhotoPath(contact.getId());
        }

        if (!TextUtils.isEmpty(etPhoneNumber.getText().toString())) {
            Phone phone = new Phone();
            phone.setId(UUIDUtil.getRandomUUID().toString());
            phone.setContactId(contact.getId());
            phone.setPhoneNumber(etPhoneNumber.getText().toString());

            contact.setPhoneList(Arrays.asList(phone));
        }

        /** save */
        mContactManager.createContact(contact, new TaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result) {
                mDialogHelper.dismissDialog();
                finish();
            }

            @Override
            public void onFail() {
                showErrorDialog(getString(R.string.error_dialog_contact_save_fail));
            }
        });
    }

    private void showProcessDialog() {
        mDialogHelper.dismissDialog();
        mDialogHelper.showProgressDialog();
    }

    private void showErrorDialog(String message) {
        mDialogHelper.dismissDialog();
        mDialogHelper.showErrorDialog(getString(R.string.error_dialog_title__contact_save_fail), message);
    }
}
