package com.chute.android.slidechute.util.intent;

import android.app.Activity;
import android.content.Intent;

import com.chute.android.slidechute.app.ChooseServiceActivity;

public class SlideChuteIntentWrapper extends IntentWrapper {

    public static final int REQUEST_CODE = 1;
    public static final String TAG = SlideChuteIntentWrapper.class.getSimpleName();

    public SlideChuteIntentWrapper(Intent intent) {
        super(intent);
    }

    public static void startSlideChute(Activity context) {
        context.startActivityForResult(new Intent(context, ChooseServiceActivity.class), REQUEST_CODE);
    }

}
