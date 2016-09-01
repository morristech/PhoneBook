package ua.testapp.phonebook.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by alexey on 26.08.16.
 */
public class ImageLoaderHelper {
    public static void loadImage(Context context, File file, ImageView targetImageView) {
        Picasso p = Picasso.with(context.getApplicationContext());
        p.load(file)
                .fit()
                .centerCrop()
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(targetImageView);
    }

    public static void loadAvatar(Context context, File file, ImageView targetImageView) {
        Picasso p = Picasso.with(context.getApplicationContext());
        p.load(file)
                .fit()
                .centerCrop()
                .transform(CropCircleTransformation.INSTANCE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(targetImageView);
    }
}
