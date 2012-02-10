package com.chute.android.photopickerplus.util.intent;

import android.app.Activity;
import android.content.Intent;

import com.chute.android.photopickerplus.app.ChooseServiceActivity;

public class PhotoPickerPlusIntentWrapper extends IntentWrapper {

    public static final int REQUEST_CODE = 1;
    public static final String TAG = PhotoPickerPlusIntentWrapper.class.getSimpleName();

    public PhotoPickerPlusIntentWrapper(Intent intent) {
        super(intent);
    }

    public static void startSlideChute(Activity context) {
        context.startActivityForResult(new Intent(context, ChooseServiceActivity.class), REQUEST_CODE);
    }

}
