package com.chute.android.photopickerplus.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.chute.android.photopickerplus.R;
import com.chute.android.photopickerplus.util.intent.PhotoPickerPlusIntentWrapper;

public class PhotoPickerPlusActivity extends Activity {

    public static final String TAG = PhotoPickerPlusActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.photo_picker_plus_activity);

	findViewById(R.id.btnChoose).setOnClickListener(new OnChooseClickListener());
    }

    private class OnChooseClickListener implements OnClickListener {

	@Override
	public void onClick(View v) {
	    PhotoPickerPlusIntentWrapper.startSlideChute(PhotoPickerPlusActivity.this);
	}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
    }
}
