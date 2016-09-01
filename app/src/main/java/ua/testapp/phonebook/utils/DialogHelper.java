package ua.testapp.phonebook.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import javax.inject.Inject;
import ua.testapp.phonebook.R;
import ua.testapp.phonebook.interfaces.TaskCompleteListener;


public class DialogHelper {

    private Context mContext;
    private Dialog mDialog;

    @Inject
    public DialogHelper() {
    }

    public void setContext (Context context){
        mContext = context;
    }

    public void showErrorDialog(String title, String message) {
        MaterialDialog.Builder adBuilder = new MaterialDialog.Builder(mContext)
                .title(title)
                .positiveText(R.string.okay)
                .positiveColorRes(R.color.colorPrimary);
        adBuilder.content(message);

        mDialog = adBuilder.show();
    }

    public void showProgressDialog() {
        MaterialDialog.Builder adBuilder = new MaterialDialog.Builder(mContext)
                .content(mContext.getString(R.string.progress_dialog_message__wait))
                .widgetColorRes(R.color.colorPrimary)
                .progress(true, 0)
                .contentGravity(GravityEnum.CENTER)
                .cancelable(false)
                .backgroundColor(ContextCompat.getColor(mContext, android.R.color.white));

        mDialog = adBuilder.show();
    }

    public void showRemoveDialog(final TaskCompleteListener taskCompleteListener) {
        MaterialDialog.Builder adBuilder = new MaterialDialog.Builder(mContext)
                .title(mContext.getString(R.string.title__contact_remove))
                .positiveText(mContext.getString(R.string.okay))
                .positiveColorRes(R.color.colorPrimary);
        adBuilder.content(mContext.getString(R.string.are_you_sure));
        adBuilder.onPositive((dialog, which) -> taskCompleteListener.onTaskComplete(true));

        adBuilder.show();
    }

    public void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
