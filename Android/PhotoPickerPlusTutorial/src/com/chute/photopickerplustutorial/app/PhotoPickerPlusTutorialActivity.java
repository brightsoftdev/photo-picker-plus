package com.chute.photopickerplustutorial.app;

import com.chute.android.photopickerplus.util.intent.PhotoActivityIntentWrapper;
import com.chute.photopickerplustutorial.R;
import com.chute.photopickerplustutorial.R.id;
import com.chute.photopickerplustutorial.R.layout;
import com.chute.photopickerplustutorial.intent.PhotoPickerPlusIntentWrapper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class PhotoPickerPlusTutorialActivity extends Activity {

    public static final String TAG = PhotoPickerPlusTutorialActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.photo_picker_plus_activity);

	findViewById(R.id.btnChoose).setOnClickListener(new OnChooseClickListener());
    }

    private class OnChooseClickListener implements OnClickListener {

	@Override
	public void onClick(View v) {
	    PhotoPickerPlusIntentWrapper.startSlideChute(PhotoPickerPlusTutorialActivity.this);
	}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (resultCode != Activity.RESULT_OK) {
	    return;
	}
	final PhotoActivityIntentWrapper wrapper = new PhotoActivityIntentWrapper(data);
	Log.d(TAG, wrapper.toString());
    }
}
