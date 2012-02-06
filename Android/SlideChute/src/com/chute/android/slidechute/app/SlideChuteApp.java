package com.chute.android.slidechute.app;

import android.app.Application;
import android.content.Context;
import android.util.TypedValue;

import com.chute.android.slidechute.R;
import com.chute.android.slidechute.util.PreferenceUtil;
import com.darko.imagedownloader.ImageLoader;

public class SlideChuteApp extends Application {

    public static final String TAG = SlideChuteApp.class.getSimpleName();

    private static ImageLoader createImageLoader(Context context) {
	ImageLoader imageLoader = new ImageLoader(context, R.drawable.placeholder);
	imageLoader.setRequiredSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
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
