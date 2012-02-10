package com.chute.android.photopickerplus.dao;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class MediaDAO {

    public static final String TAG = MediaDAO.class.getSimpleName();

    private MediaDAO() {
    }

    public static Cursor getCameraPhotos(final Context context) {
        final String[] projection = new String[] {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        final Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        final String query = MediaStore.Images.Media.DATA + " LIKE \"%DCIM%\"";
        return context.getContentResolver().query(images, projection, query, null, null);
    }

    public static Cursor getAllMediaPhotos(final Context context) {
        final String[] projection = new String[] {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        final Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        return context.getContentResolver().query(images, projection, null, null, null);
    }
}
