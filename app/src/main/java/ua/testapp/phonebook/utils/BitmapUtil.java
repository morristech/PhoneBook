package ua.testapp.phonebook.utils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import javax.inject.Inject;

public class BitmapUtil {

    private ContentUtil mContentUtil;

    @Inject
    public BitmapUtil(ContentUtil contentUtil) {
        mContentUtil = contentUtil;
    }

    public Bitmap createOriginalBitmapFromPath(Uri originalPath, Uri dataIntent) {
        String path = mContentUtil.getOriginalPath(originalPath);
        if (path == null) {
            return null;
        }

        Bitmap image = BitmapFactory.decodeFile(path);
        if (image == null && dataIntent != null) {
            path = mContentUtil.getOriginalPath(dataIntent);
            image = BitmapFactory.decodeFile(path);
        }
        return image;
    }

    public Bitmap createBitmapFromPath(String originalPath) {
        if (originalPath == null) {
            return null;
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        options.inTempStorage = new byte[16 * 1024];

        return BitmapFactory.decodeFile(originalPath, options);
    }
}
