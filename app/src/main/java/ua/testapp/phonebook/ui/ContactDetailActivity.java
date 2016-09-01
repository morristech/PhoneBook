package ua.testapp.phonebook.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
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
import ua.testapp.phonebook.utils.SimpleTextWatcher;
import ua.testapp.phonebook.utils.UIHelper;
import ua.testapp.phonebook.utils.UUIDUtil;
import ua.testapp.phonebook.utils.UsPhoneNumberFormatter;

/**
 * Created by alexey on 26.08.16.
 */
public class ContactDetailActivity extends AbstractBaseActivity {
    public static final String CONTACT_ARG = "contact_arg";
    private static final String TAG = ContactDetailActivity.class.getSimpleName();

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
    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PhoneBookApplication) getApplicationContext()).getGeneralComponent().inject(this);

        mIsGrantedPermission = PermissionUtil.requestCheckPermissions(this);
        mDialogHelper.setContext(this);

        actionInitPhoto();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_contact_detail;
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initContent() {
        /** get contact */
        mContact = getIntent().getParcelableExtra(CONTACT_ARG);

        Log.i(TAG, mContact.toString());

        /** init title */
        setTitleToolBar(getString(R.string.app_name));

        /** init data contact */
        etName.setText(mContact.getName());

        if (!TextUtils.isEmpty(mContact.getSurname())) {
            etSurname.setText(mContact.getSurname());
        }

        etPhoneNumber.setText(mContact.getPhoneList().get(0).getPhoneNumber());

        if (!TextUtils.isEmpty(mContact.getPosition())) {
            etPosition.setText(mContact.getPosition());
        }

        if (!TextUtils.isEmpty(mContact.getEmail())) {
            etEmail.setText(mContact.getEmail());
        }

        if (!TextUtils.isEmpty(mContact.getContactLink())) {
            etContactLink.setText(mContact.getContactLink());
        }

        /** add phone formatter */
        UsPhoneNumberFormatter addLineNumberFormatter = new UsPhoneNumberFormatter(new WeakReference<>(etPhoneNumber), etPhoneNumber);
        etPhoneNumber.addTextChangedListener(addLineNumberFormatter);

        /** init name listener */
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_contact_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_done:
                actionUpdateContact();
                break;
            case R.id.action_call:
                actionCallPhoneNumber();
                break;
            case R.id.action_camera:
                actionOpenCamera();
                break;
            case R.id.action_gallery:
                actionOpenGallery();
                break;
            case R.id.action_remove:
                actionRemoveContact();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.iv_show_link)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_show_link:
                actionShowLinkInBrowser();
                break;
            default:
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mIsGrantedPermission = PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void actionInitPhoto() {
        if (!TextUtils.isEmpty(mContact.getPhotoPath())) {
            ivPhoto.setVisibility(View.VISIBLE);
            ImageLoaderHelper.loadImage(this, mFileUtil.getFileByFileName(mContact.getPhotoPath()), ivPhoto);
        } else {
            ivPhoto.setVisibility(View.GONE);
        }
    }

    private void actionShowLinkInBrowser(){

        if (!TextUtils.isEmpty(etContactLink.getText()) && (mContact.getContactLink().startsWith("http://") || mContact.getContactLink().startsWith("https://"))) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(etContactLink.getText().toString()));
            startActivity(browserIntent);
        } else {
            UIHelper.showSnackbar(rootView,getString(R.string.error_open_webview));
        }

    }

    private void actionCallPhoneNumber() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:".concat(mContact.getPhoneList().get(0).getPhoneNumber().replaceAll("[^\\d]", ""))));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            PermissionUtil.requestPermissionCallPhone(this);
            return;
        }
        startActivity(callIntent);
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

    private void actionUpdateContact() {
        UIHelper.hideKeyboard(this);

        if (checkContactFields()) {
            showProcessDialog();
            if (mImageBitmap != null) {
                mExternalStorageUtil.saveImageFile(mContact.getId(), mImageBitmap, new TaskCompleteListener<Boolean>() {
                    @Override
                    public void onTaskComplete(Boolean result) {
                        if (result) {
                            initAndUpdateContact(mContact);
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
                initAndUpdateContact(mContact);
            }
        }

    }

    private void actionRemoveContact() {
        mDialogHelper.showRemoveDialog(new TaskCompleteListener<Boolean>() {
            @Override
            public void onTaskComplete(Boolean result) {
                if (result) {
                    removeContacts();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void removeContacts() {
        mContactManager.removeContacts(Arrays.asList(mContact), new TaskCompleteListener<Boolean>() {
            @Override
            public void onTaskComplete(Boolean result) {
                if (result) {
                    finish();
                }
            }

            @Override
            public void onFail() {

            }
        });
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

    private void initAndUpdateContact(Contact contact){
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
            Phone phone = mContact.getPhoneList().get(0);
            phone.setPhoneNumber(etPhoneNumber.getText().toString());
            contact.setPhoneList(Arrays.asList(phone));
        }

        /** update */
        mContactManager.updateContact(contact, new TaskCompleteListener<Boolean>() {
            @Override
            public void onTaskComplete(Boolean result) {
                mDialogHelper.dismissDialog();
                if (result) {
                    UIHelper.showSnackbar(rootView, getString(R.string.contact_updated));
                } else {
                    UIHelper.showSnackbar(rootView, getString(R.string.error_dialog_contact_save_fail));
                }
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
