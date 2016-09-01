package ua.testapp.phonebook.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import ua.testapp.phonebook.constants.IntentData;

/**
 * Created by alexey on 30.08.16.
 */
public class PermissionUtil {

    @SuppressLint("InlinedApi")
    public static boolean requestCameraAndExternalStoragePermissions(Context context) {
        if (!checkCameraPermissionGranted(context)
                && !checkReadStoragePermissionGranted(context)
                && !checkWriteStoragePermissionGranted(context)) {

            return requestPermissions(context);
        } else {
            return true;
        }
    }

    @SuppressLint("InlinedApi")
    public static boolean requestCheckPermissions(Context context) {
        return checkCameraPermissionGranted(context)
                && checkReadStoragePermissionGranted(context)
                && checkWriteStoragePermissionGranted(context);
    }

    private static boolean checkCameraPermissionGranted(Context context) {
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    private static boolean checkWriteStoragePermissionGranted(Context context) {
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    private static boolean checkReadStoragePermissionGranted(Context context) {
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkCallPhonePermissionGranted(Context context) {
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED;
    }

    private static boolean requestPermissions (Context context){
        ActivityCompat.requestPermissions((Activity) context,
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                IntentData.REQUEST_PERMISSIONS_PHONE_BOOK);
        return false;
    }

    public static boolean requestPermissionCallPhone (Context context){
        ActivityCompat.requestPermissions((Activity) context,
                new String[]{Manifest.permission.CALL_PHONE},
                IntentData.REQUEST_PERMISSIONS_PHONE_BOOK);
        return false;
    }

    public static boolean onRequestPermissionsResult(int requestCode,
                                              String permissions[], int[] grantResults) {
        switch (requestCode) {
            case IntentData.REQUEST_PERMISSIONS_PHONE_BOOK: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("Permissions granted!");
                    return true;
                } else {
                    System.out.println("Permissions blocked!");
                    return false;
                }
            }
        }
        return false;
    }
}
