package ua.testapp.phonebook.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import ua.testapp.phonebook.constants.AppConfig;
import ua.testapp.phonebook.interfaces.TaskCompleteListener;

public class FileUtil {

    public static final String TAG = FileUtil.class.getSimpleName();

    private static Context mContext;

    @Inject
    public FileUtil(Context context) {
        mContext = context;
    }

    public static File getCacheDirectory() {
        return new File(mContext.getExternalCacheDir().getAbsolutePath() + File.separator + AppConfig.EXTERNAL_STORAGE_FOLDER_NAME);
    }

    public File createFileInCache(String fileName, String extentionWithDot) {
        File directory = new File(mContext.getExternalCacheDir().getAbsolutePath() + File.separator
                + AppConfig.EXTERNAL_STORAGE_FOLDER_NAME + File.separator);
        directory.mkdirs();

        File file = new File(directory, fileName + extentionWithDot);

        if (file.exists()) {
            if (file.delete()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public File createImageAvatarFile() throws IOException {
        // Create an image file name
        String imageFileName = PreferenceHelper.getUserLogin();
        File image = createFileInCache(imageFileName, ".jpg");

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }

    private String mCurrentPhotoPath = null;
    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File image = createFileInCache(imageFileName, ".jpg");

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private String getCurrentPhotoPath() {
        return mCurrentPhotoPath;
    }

    public String getLastImagePath() {
        if (getCurrentPhotoPath() != null) {
            return Uri.parse(getCurrentPhotoPath()).getPath();
        } else {
            return null;
        }
    }

    private String getFileDirByFileName(String fileName) {
        return getCacheDirectory() + File.separator + fileName + ".jpg";
    }

    public File getFileByFileName(String fileName) {
        return new File(getFileDirByFileName(fileName));
    }
}
