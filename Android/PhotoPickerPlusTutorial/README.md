Introduction
====

PhotoPickerPlusTutorial is a tutorial project that shows how to use the PhotoPicker+ component. It contains Chute SDK library as well as PhotoPicker+ library. This tutorial enables browsing albums and photos on your social services and device gallery, selecting a photo and display of the selected photo and its data.

![IMG_TUTORIAL1](https://github.com/chute/photo-picker-plus/raw/master/Android/PhotoPickerPlusTutorial/screenshots/IMG_Tutorial1.png)![IMG_TUTORIAL2](https://github.com/chute/photo-picker-plus/raw/master/Android/PhotoPickerPlusTutorial/screenshots/IMG_Tutorial2.png)

Setup
====

* Follow the ProjectSetup tutorial that can be found and downloaded at  
  [https://github.com/chute/chute-tutorials/tree/master/Android/ProjectSetup](https://github.com/chute/chute-tutorials/tree/master/Android/ProjectSetup) for a complete guide on how to setup the chute SDK.
  
* Additionally you can visit http://developer.getchute.com/ for more information about creating a new developer account and obtaining chute credentials.
  You can create a Chute developer account and make a new app in Chute at http://apps.getchute.com/
	*  For the URL you can enter http://getchute.com/ if you don't have a site for your app.
	*  For the Callback URL you can use http://getchute.com/oauth/callback if you don't need callbacks for another purpose.
	
	![IMG_TUTORIAL3](https://github.com/chute/photo-picker-plus/raw/master/Android/PhotoPickerPlusTutorial/screenshots/IMG_Tutorial3.png)![IMG_TUTORIAL4](https://github.com/chute/photo-picker-plus/raw/master/Android/PhotoPickerPlusTutorial/screenshots/IMG_Tutorial4.png)  
  
* Add the PhotoPicker+ component to your project by either copying all the resources and source code or by adding it as an Android Library project.

* The next thing you need to do is register the activities, services and the application class into the AndroidManifest.xml file:

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
This class is the extended Application class. It is registered inside the "application" tag in the manifest and is used for initializing the utility classes used in the component.
PhotoPickerPlusTutorialApp can extend PhotoPickerPlusApp like shown in this tutorial:

<pre><code>
public class PhotoPickerPlusTutorialApp extends PhotoPickerPlusApp {

}
</code></pre>

This way the developer can use his own methods and code inside the Application class. 

If the developer decides to extend the Application class instead of PhotoPickerPlusApp he must copy the all the code below:

<pre><code>
public class PhotoPickerPlusTutorialApp extends Application {
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
</code></pre>

PhotoPickerPlusTutorialApp can also be neglected by registering PhotoPickerPlusApp into the manifest instead of PhotoPickerPlusTutoiralApp if the developer doesn't have the need for extending the Application class.

##PhotoPickerPlusTutorialActivity.java 
This class is an Activity class. It contains a Button and an ImageView. When the button is clicked, PhotoPickerPlusIntentWrapper starts ChooseServiceActivity. PhotoPickerPlusIntentWrapper is a wrapper class that wraps the parameters needed for the intent.

<pre><code>
private class OnPhotoPickerClickListener implements OnClickListener {
	@Override
	public void onClick(View v) {
	    PhotoPickerPlusIntentWrapper.startPhotoPicker(PhotoPickerPlusTutorialActivity.this);
	}
    }
</code></pre>

ChooseServiceActivity contains a list of services and device photos albums. You can authenticate using Facebook, Flickr, Instagram and Picasa, browse albums and photos, browse device photos as well as take a photo with the camera. 
After clicking a photo, a result is returned to the activity that started the component where the selected photo is displayed using the ImageLoader.

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

PhotoActivityIntentWrapper encapsulates a couple of different information available for the selected image. Keep in mind that some of that additional info might be null depending of its availability. Also the different paths inside the Media model can point to the same location if there are no additional sizes available.


    
      

    
      