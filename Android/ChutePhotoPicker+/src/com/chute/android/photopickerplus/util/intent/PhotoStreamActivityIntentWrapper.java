package com.chute.android.photopickerplus.util.intent;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.chute.android.photopickerplus.app.PhotoStreamActivity;
import com.chute.sdk.model.GCAccountMediaModel;

public class PhotoStreamActivityIntentWrapper {

    public static final int ACTIVITY_FOR_RESULT_STREAM_KEY = 113;

    private static final String EXTRA_KEY_PATH_LIST = "key_path_list";
    private static final String EXTRA_KEY_PATH = "key_path";
    private static final String EXTRA_KEY_CURSOR_PHOTOS = "cursor_photos";
    private static final String EXTRA_KEY_PHOTOMODEL="photoModel";

    public static final int TYPE_CAMERA_ROLL = 0;
    public static final int TYPE_ALL_PHOTOS = 1;

    @SuppressWarnings("unused")
    private static final String TAG = PhotoStreamActivityIntentWrapper.class.getSimpleName();

    private final Intent intent;

    public PhotoStreamActivityIntentWrapper(Intent intent) {
	super();
	this.intent = intent;
    }

    public PhotoStreamActivityIntentWrapper(Context packageContext, Class<?> cls) {
	super();
	intent = new Intent(packageContext, cls);
    }

    public PhotoStreamActivityIntentWrapper(Context packageContext) {
	super();
	intent = new Intent(packageContext, PhotoStreamActivity.class);
    }

    public Intent getIntent() {
	return intent;
    }

    public void setAssetPathList(ArrayList<String> pathList) {
	intent.putStringArrayListExtra(EXTRA_KEY_PATH_LIST, pathList);
    }

    public ArrayList<String> getAssetPathList() {
	return intent.getExtras().getStringArrayList(EXTRA_KEY_PATH_LIST);
    }

    public void setAssetPath(String path) {
	intent.putExtra(EXTRA_KEY_PATH, path);
    }

    public String getAssetPath() {
	return intent.getExtras().getString(EXTRA_KEY_PATH);
    }

    public int getFilterType() {
	return intent.getExtras().getInt(EXTRA_KEY_CURSOR_PHOTOS);
    }

    public void setFilterType(int type) {
	intent.putExtra(EXTRA_KEY_CURSOR_PHOTOS, type);
    }
    
    public GCAccountMediaModel getMediaModel() {
    	return getIntent().getExtras().getParcelable(EXTRA_KEY_PHOTOMODEL);
        }

        public void setMediaModel(GCAccountMediaModel model) {
    	getIntent().putExtra(EXTRA_KEY_PHOTOMODEL, model);
        }

    public void startActivityForResult(Activity context, int code) {
	context.startActivityForResult(intent, code);
    }
}
