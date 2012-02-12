package com.chute.photopickerplustutorial.app;

import com.chute.android.photopickerplus.util.intent.PhotoActivityIntentWrapper;
import com.chute.photopickerplustutorial.R;
import com.chute.photopickerplustutorial.R.id;
import com.chute.photopickerplustutorial.R.layout;
import com.chute.photopickerplustutorial.intent.PhotoPickerPlusIntentWrapper;
import com.darko.imagedownloader.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class PhotoPickerPlusTutorialActivity extends Activity {

    public static final String TAG = PhotoPickerPlusTutorialActivity.class.getSimpleName();
	private ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.photo_picker_plus_activity);

	findViewById(R.id.btnPhotoPicker).setOnClickListener(new OnPhotoPickerClickListener());
	image = (ImageView) findViewById(R.id.imageView);
	
    }

    private class OnPhotoPickerClickListener implements OnClickListener {

	@Override
	public void onClick(View v) {
	    PhotoPickerPlusIntentWrapper.startPhotoPicker(PhotoPickerPlusTutorialActivity.this);
	}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (resultCode != Activity.RESULT_OK) {
	    return;
	}
	final PhotoActivityIntentWrapper wrapper = new PhotoActivityIntentWrapper(data);
	ImageLoader.get(this).displayImage(wrapper.getMediaModel().getUrl(), image);
	Log.d(TAG, wrapper.toString());
    }
}
