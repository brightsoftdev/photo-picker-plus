package com.chute.android.photopickerplus.app;

import android.app.Application;
import android.content.Context;
import android.util.TypedValue;

import com.chute.android.photopickerplus.util.PreferenceUtil;
import com.chute.android.photopickerplus.R;
import com.darko.imagedownloader.ImageLoader;

public class PhotoPickerPlusApp extends Application {

    public static final String TAG = PhotoPickerPlusApp.class.getSimpleName();

    private static ImageLoader createImageLoader(Context context) {
	ImageLoader imageLoader = new ImageLoader(context, R.drawable.placeholder);
	imageLoader.setDefaultImageSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
		75, context.getResources().getDisplayMetrics()));
	return imageLoader;
    }

    private ImageLoader mImageLoader;

    @Override
    public void onCreate() {
	super.onCreate();
	mImageLoader = createImageLoader(this);
	PreferenceUtil.init(getApplicationContext());
    }

    @Override
    public Object getSystemService(String name) {
	if (ImageLoader.IMAGE_LOADER_SERVICE.equals(name)) {
	    return mImageLoader;
	} else {
	    return super.getSystemService(name);
	}
    }

}
