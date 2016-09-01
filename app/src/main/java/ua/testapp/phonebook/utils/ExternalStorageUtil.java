package ua.testapp.phonebook.utils;

import android.graphics.Bitmap;
import java.io.File;
import java.io.FileOutputStream;
import javax.inject.Inject;

import ua.testapp.phonebook.interfaces.TaskCompleteListener;

public class ExternalStorageUtil {
    public static final String TAG = ExternalStorageUtil.class.getSimpleName();

    @Inject
    public ExternalStorageUtil() {
    }

    public void saveImageFile(String name, Bitmap finalBitmap, TaskCompleteListener<Boolean> taskCompleteListener) {
        FileUtil.getCacheDirectory().mkdirs();
        String fileName = name + ".jpg";
        File file = new File (FileUtil.getCacheDirectory(), fileName);
        if (file.exists()) file.delete();
        try {
            if (finalBitmap != null) {
                FileOutputStream out = new FileOutputStream(file);
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
                taskCompleteListener.onTaskComplete(true);
            } else {
                taskCompleteListener.onTaskComplete(false);
            }
        } catch (Exception e) {
            taskCompleteListener.onTaskComplete(false);
            e.printStackTrace();
        }
    }

    public void deleteImageFile(String name, TaskCompleteListener<Boolean> taskCompleteListener) {
        File file = new File (FileUtil.getCacheDirectory(), name);
        boolean isDeleted = file.delete();
        if (isDeleted) {
            taskCompleteListener.onTaskComplete(isDeleted);
        } else {
            taskCompleteListener.onFail();
        }
    }
}
