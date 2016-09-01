package ua.testapp.phonebook.ui.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.R;
import ua.testapp.phonebook.interfaces.ContactActionListener;
import ua.testapp.phonebook.interfaces.TaskCompleteListener;
import ua.testapp.phonebook.model.Contact;
import ua.testapp.phonebook.ui.ContactDetailActivity;
import ua.testapp.phonebook.ui.LoginActivity;
import ua.testapp.phonebook.utils.FileUtil;
import ua.testapp.phonebook.utils.ImageLoaderHelper;
import ua.testapp.phonebook.utils.PermissionUtil;

/**
 * Created by alexey on 31.08.16.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> mContactsSelectedList = new ArrayList<>();
    private List<Contact> mContactsList = new ArrayList<>();
    private TaskCompleteListener<Boolean> mTaskCompleteListener;
    private ContactActionListener<Contact> mOnContactActionListener;
    private boolean mIsMultiSelectMode;

    @Inject
    FileUtil mFileUtil;

    private Context mContext;

    public ContactAdapter(Context context) {
        mContext = context;
        mIsMultiSelectMode = false;
        ((PhoneBookApplication) context.getApplicationContext()).getGeneralComponent().inject(this);
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mContactsList.size();
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder vh, int position) {
        vh.mContact = mContactsList.get(position);
        String userData = "";
        if (!TextUtils.isEmpty(vh.mContact.getSurname())) {
            userData += vh.mContact.getSurname().concat(" ");
        }
        userData += vh.mContact.getName();

        vh.tvUserData.setText(userData);
        vh.tvPhoneNumber.setText("+38 ".concat(vh.mContact.getPhoneList().get(0).getPhoneNumber()));

        if (mContactsSelectedList.contains(vh.mContact)) {
            vh.llContainer.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_grey_color));
        } else {
            vh.llContainer.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.white));
        }

        //load image
        if (!TextUtils.isEmpty(vh.mContact.getPhotoPath())) {
            ImageLoaderHelper.loadAvatar(mContext, mFileUtil.getFileByFileName(vh.mContact.getPhotoPath()), vh.ivAvatar);
        } else {
            vh.ivAvatar.setImageResource(R.drawable.ic_account_circle);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView ivAvatar;
        TextView tvUserData;
        TextView tvPhoneNumber;
        LinearLayout llContainer;
        ImageView ivCall;
        Contact mContact;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tvUserData = (TextView) itemView.findViewById(R.id.tv_user_data);
            tvPhoneNumber = (TextView) itemView.findViewById(R.id.tv_phone_number);
            ivCall = (ImageView) itemView.findViewById(R.id.iv_call_phone);
            llContainer = (LinearLayout) itemView.findViewById(R.id.ll_container);

            itemView.setOnClickListener(this);
            itemView.setLongClickable(true);
            itemView.setOnLongClickListener(this);

            ivCall.setOnClickListener(view -> actionCallPhoneNumber(mContact));
        }

        @Override
        public void onClick(View v) {
            actionClickItemContact(mContact, llContainer);
        }

        @Override
        public boolean onLongClick(View v) {
            if (!mIsMultiSelectMode) {
                mIsMultiSelectMode = true;
                if (!mContactsSelectedList.contains(mContact)) {
                    llContainer.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_grey_color));
                    mContactsSelectedList.add(mContact);
                    mOnContactActionListener.onAddContact(mContact);
                }
            } else {
                mIsMultiSelectMode = false;
                mTaskCompleteListener.onTaskComplete(mIsMultiSelectMode);
            }

            return true;
        }
    }

    private void actionCallPhoneNumber(Contact contact) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:".concat(contact.getPhoneList().get(0).getPhoneNumber().replaceAll("[^\\d]", ""))));

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            PermissionUtil.requestPermissionCallPhone(mContext);
            return;
        }
        mContext.startActivity(callIntent);
    }

    public void setOnItemLongClickListener(TaskCompleteListener taskCompleteListener) {
        mTaskCompleteListener = taskCompleteListener;
    }

    private void actionClickItemContact(Contact contact, View selectedItemView) {
        if (!mIsMultiSelectMode) {
            actionOpenContactDetail(contact);
        } else {
            if (!mContactsSelectedList.contains(contact)) {
                selectedItemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_grey_color));
                mContactsSelectedList.add(contact);
                mOnContactActionListener.onAddContact(contact);
            } else {
                selectedItemView.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.white));
                mContactsSelectedList.remove(contact);
                mOnContactActionListener.onRemoveContact(contact);
            }

            if (mContactsSelectedList.isEmpty()) {
                mIsMultiSelectMode = false;
            }
        }

    }

    private void actionOpenContactDetail(Contact contact) {
        Intent intent = new Intent(mContext, ContactDetailActivity.class);
        intent.putExtra(ContactDetailActivity.CONTACT_ARG, contact);
        mContext.startActivity(intent);
    }

    public void setContacts(List<Contact> contactList) {
        mContactsList.clear();
        mContactsList.addAll(contactList);
        notifyDataSetChanged();
    }


    public void setIsMultiSelectMode(boolean mIsMultiSelectMode) {
        this.mIsMultiSelectMode = mIsMultiSelectMode;
    }

    public void setOnContactActionListener(ContactActionListener<Contact> onContactContactActionListener) {
        this.mOnContactActionListener = onContactContactActionListener;
    }

    public void clearSelectedContacts() {
        mContactsSelectedList.clear();
        notifyDataSetChanged();
    }
}
