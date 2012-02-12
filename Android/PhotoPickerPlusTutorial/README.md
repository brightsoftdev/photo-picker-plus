Introduction
====

PhotoPickerPlusTutorial is a tutorial project that shows how to use the PhotoPicker+ component. It contains Chute SDK library as well as PhotoPicker+ library. This tutorial enables browsing albums and photos on your social services and device gallery, selecting a photo and display of the selected photo and its data.


Setup
====

* Follow the ProjectSetup tutorial that can be found and downloaded at  
  [https://github.com/chute/chute-tutorials/tree/master/Android/ProjectSetup](https://github.com/chute/chute-tutorials/tree/master/Android/ProjectSetup).
  
* Copy the resources into your project.

* Register the activities into the AndroidManifest.xml file:

    ```
        <application
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:name=".app.PhotoPickerPlusTutorialApp"
        android:theme="@android:style/Theme.Light.NoTitleBar" >
        <service android:name="com.chute.sdk.api.GCHttpService" />

        <activity
            android:label="@string/app_name"
            android:name=".app.PhotoPickerPlusTutorialActivity"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Light.NoTitleBar" >
            <intent-filter >
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.chute.android.photopickerplus.app.ChooseServiceActivity"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Light.NoTitleBar" >
        </activity>
        <activity
            android:name="com.chute.android.photopickerplus.app.AlbumsActivity"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Light.NoTitleBar" >
        </activity>
        <activity
            android:name="com.chute.sdk.api.authentication.GCAuthenticationActivity"
            android:theme="@android:style/Theme.Light.NoTitleBar" >
        </activity>
        <activity
            android:name="com.chute.android.photopickerplus.app.PhotoStreamActivity"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Light.NoTitleBar" >
        </activity>
        <activity
            android:name="com.chute.android.photopickerplus.app.PhotosActivity"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Light.NoTitleBar" >
        </activity>
        </application>
    ```


Usage
====

##PhotoPickerPlusTutorialApp.java 
This class is the Application class. It is registered using the "application" tag in the manifest and is used for initializing utility classes such as SharedPreferences, ImageLoader etc. 
PhotoPickerPlusTutorialApp can extend PhotoPickerPlusApp like shown in this tutorial:
<code><pre>
public class PhotoPickerPlusTutorialApp extends PhotoPickerPlusApp {

}
</pre></code>
In this way the developer can initialize other utility classes by just overriding the onCreate() method. 
PhotoPickerPlusTutorialApp can also extend Application:
<code><pre>
public class PhotoPickerPlusTutorialApp extends Application {
    public static final String TAG = PhotoPickerPlusTutorialApp.class.getSimpleName();

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
</pre></code>
PhotoPickerPlusTutorialApp can be also neglected by registering PhotoPickerPlusApp into the manifest instead of PhotoPickerPlusTutoiralApp.

##PhotoPickerPlusTutorialActivity.java 
This class is an Activity class. It contains a Button and an ImageView. When the button is clicked, PhotoPickerPlusIntentWrapper starts ChooseServiceActivity. PhotoPickerPlusIntentWrapper is a wrapper class that wraps the parameters needed for the intent.
<code><pre>
private class OnPhotoPickerClickListener implements OnClickListener {
	@Override
	public void onClick(View v) {
	    PhotoPickerPlusIntentWrapper.startPhotoPicker(PhotoPickerPlusTutorialActivity.this);
	}
    }
</pre></code>
ChooseServiceActivity contains a list of services and device photos albums is shown. You can authenticate using Facebook, Flickr, Instagram and Picasa, browse albums and photos, browse device photos as well as take a photo with the camera. 
After clicking a photo, we go back at PhotoPickerPlusTutorialActivity where the selected photo is displayed using the ImageLoader.
<pre><code>
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
</code></pre>
PhotoActivityIntentWrapper displays the selected photo data as GCAccountMediaModel.


    
      

    
      