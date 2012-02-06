package com.chute.android.slidechute.util.intent;

import java.util.ArrayList;

import com.chute.android.slidechute.app.PhotoStreamActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class CameraRollActivityIntentWrapper {

	public static final int ACTIVITY_FOR_RESULT_CAMERA_KEY = 113;

	private static final String EXTRA_KEY_PATH_LIST = "key_path_list";
	private static final String EXTRA_KEY_PATH = "key_path";
	private static final String EXTRA_KEY_CURSOR_CAMERA = "cursor_camera";

	@SuppressWarnings("unused")
	private static final String TAG = CameraRollActivityIntentWrapper.class
			.getSimpleName();

	private final Intent intent;

	public CameraRollActivityIntentWrapper(Intent intent) {
		super();
		this.intent = intent;
	}

	public CameraRollActivityIntentWrapper(Context packageContext, Class<?> cls) {
		super();
		intent = new Intent(packageContext, cls);
	}

	public CameraRollActivityIntentWrapper(Context packageContext) {
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

	public Boolean getCameraBoolean() {
		return intent.getExtras().getBoolean(EXTRA_KEY_CURSOR_CAMERA);
	}

	public void setCameraBoolean(Boolean cameraBoolean) {
		intent.putExtra(EXTRA_KEY_CURSOR_CAMERA, cameraBoolean);
	}

	public void startActivityForResult(Activity context, int code) {
		context.startActivityForResult(intent, code);
	}
}
