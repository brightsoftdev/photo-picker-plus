package com.chute.android.photopickerplus.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;

import com.chute.sdk.utils.GCUtils;

public class AppUtil {

    @SuppressWarnings("unused")
    private static final String TAG = AppUtil.class.getSimpleName();

    private static String SDCARD_FOLDER_CACHE = Environment.getExternalStorageDirectory()
	    + "/Android/data/%s/files/";

    public static String getThumbSmallUrl(String urlNormal) {
	return GCUtils.getCustomSizePhotoURL(urlNormal, 100, 100);
    }

    public static File getTempFile(Context context) {
	final File path = getAppCacheDir(context);
	if (!path.exists()) {
	    path.mkdirs();
	}
	File f = new File(path, "temp_image.jpg");
	if (f.exists() == false) {
	    try {
		f.createNewFile();
	    } catch (IOException e) {
		Log.w(TAG, e.getMessage(), e);
	    }
	}
	return f;
    }

    public static File getAppCacheDir(Context context) {
	return new File(String.format(SDCARD_FOLDER_CACHE, context.getPackageName()));
    }

    public static boolean hasImageCaptureBug() {
	// list of known devices that have the bug
	ArrayList<String> devices = new ArrayList<String>();
	devices.add("android-devphone1/dream_devphone/dream");
	devices.add("generic/sdk/generic");
	devices.add("vodafone/vfpioneer/sapphire");
	devices.add("tmobile/kila/dream");
	devices.add("verizon/voles/sholes");
	devices.add("google_ion/google_ion/sapphire");
	devices.add("SEMC/X10i_1232-9897/X10i");

	return devices.contains(android.os.Build.BRAND + "/" + android.os.Build.PRODUCT + "/"
		+ android.os.Build.DEVICE);
    }

    public static String getPath(Context context, Uri uri) throws NullPointerException {
	final String[] projection = { MediaColumns.DATA };
	final Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
	final int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
	cursor.moveToFirst();
	return cursor.getString(column_index);
    }
    
    public final static String asUpperCaseFirstChar(final String target) {

        if ((target == null) || (target.length() == 0)) {
            return target; 
        }
        return Character.toUpperCase(target.charAt(0))
                + (target.length() > 1 ? target.substring(1) : "");
    }
}
