package com.chute.photopickerplustutorial.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.chute.android.photopickerplus.util.intent.PhotoActivityIntentWrapper;
import com.chute.photopickerplustutorial.R;
import com.chute.photopickerplustutorial.intent.PhotoPickerPlusIntentWrapper;
import com.darko.imagedownloader.ImageLoader;

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

	String path;
	Uri uri = Uri.parse(wrapper.getMediaModel().getUrl());
	if (uri.getScheme().contentEquals("http")) {
	    path = uri.toString();
	} else {
	    path = uri.getPath();
	}
	Log.d(TAG, "The Path or url of the file " + path);
    }
}
