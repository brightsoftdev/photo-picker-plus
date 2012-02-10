package com.chute.android.photopickerplus.util;

import com.chute.android.photopickerplus.app.AlbumsActivity;
import com.chute.android.photopickerplus.R;
import com.chute.android.photopickerplus.R.id;
import com.chute.android.photopickerplus.R.layout;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationUtil {
    public static final String TAG = NotificationUtil.class.getSimpleName();

    private NotificationUtil() {
    }

    public static void makeToast(Context context, int resId) {
	makeToast(context, context.getString(resId));
    }

    public static void makeToast(Context context, String message) {
	Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void makeServerErrorToast(Context context) {
	makeToast(context, R.string.http_error);
    }

    public static void makeServerErrorToast(Context context, String message) {
	makeToast(context, message + ", " + context.getString(R.string.http_error));
    }

    public static void makeConnectionProblemToast(Context context) {
	makeToast(context, R.string.http_exception);
    }

    public static void makeConnectionProblemToast(Context context, String message) {
	makeToast(context, message + ", " + context.getString(R.string.http_exception));
    }

    public static void makeParserErrorToast(Context context) {
	makeToast(context, R.string.parsing_exception);
    }

    public static void makeParserErrorToast(Context context, String message) {
	makeToast(context, message + ", " + context.getString(R.string.parsing_exception));
    }

    public static void showPhotosAdapterToast(Context context, int count) {
        View layout = LayoutInflater.from(context).inflate(R.layout.dialog_loaded_photos, null);
        String text = "Loaded " + count + " photos in this album";
        TextView toastText = (TextView) layout.findViewById(R.id.txt_dialog_loaded_photos);
        toastText.setText(text);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    
    public static void showAlbumsAdapterToast(Context context, int count) {
        View layout = LayoutInflater.from(context).inflate(R.layout.dialog_loaded_photos, null);
        String text = "Loaded " + count + " albums";
        TextView toastText = (TextView) layout.findViewById(R.id.txt_dialog_loaded_photos);
        toastText.setText(text);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    
}
