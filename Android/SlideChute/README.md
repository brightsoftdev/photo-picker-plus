
Introduction
====

SlideChute is an Android application which allows you browsing your albums and photos 
that can be found on your device, as well as browsing albums and photos on your Facebook,
Flickr, Picasa or Instagram account. This app includes SDK library, logs into Facebook,
Flickr, Picasa or Instagram, displays list of albums and photo gallery of the selected album.

![SS_Slidechute1](https://github.com/chute/Chute-Android-Kitchen-Sink/raw/master/SlideChute/screenshots/SS_Slidechute1.png)![SS_Slidechute2](https://github.com/chute/Chute-Android-Kitchen-Sink/raw/master/SlideChute/screenshots/SS_Slidechute2.png)![SS_Slidechute3](https://github.com/chute/Chute-Android-Kitchen-Sink/raw/master/SlideChute/screenshots/SS_Slidechute3.png)![SS_Slidechute4](https://github.com/chute/Chute-Android-Kitchen-Sink/raw/master/SlideChute/screenshots/SS_Slidechute4.png)

Setup
====

* Follow the ProjectSetup tutorial that can be found and downloaded at  
  [https://github.com/chute/chute-tutorials/tree/master/Android/ProjectSetup](https://github.com/chute/chute-tutorials/tree/master/Android/ProjectSetup).
  
* Copy the resources into your project.

* Register the activities into the AndroidManifest.xml file:

    ```
         <application
     android:name=".app.SlideChuteApp"
     android:debuggable="true"
     android:icon="@drawable/ic_launcher"
     android:theme="@android:style/Theme.Light.NoTitleBar" >
    <service android:name="com.chute.sdk.api.GCHttpService" />

    <activity
      android:name=".app.ChooseServiceActivity"
      android:screenOrientation="portrait"
      android:theme="@android:style/Theme.Light.NoTitleBar" >
    </activity>
    <activity
      android:name=".app.AlbumsActivity"
      android:screenOrientation="portrait"
      android:theme="@android:style/Theme.Light.NoTitleBar" >
    </activity>
    <activity
      android:name="com.chute.sdk.api.authentication.GCAuthenticationActivity"
      android:theme="@android:style/Theme.Light.NoTitleBar" >
    </activity>
    <activity
      android:name=".app.PhotoStreamActivity"
      android:screenOrientation="portrait"
      android:theme="@android:style/Theme.Light.NoTitleBar" >
    </activity>
    <activity
      android:name=".app.PhotosActivity"
      android:screenOrientation="portrait"
      android:theme="@android:style/Theme.Light.NoTitleBar" >
    </activity>
    <activity android:name=".app.SlideChuteActivity" >
      <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
        </activity>
        </application>
    ```    
    
Usage
====

## ChooseService Activity
ChooseServiceActivity displays the first screen. It consists of ListView containing device
photos and online photos. 
When "Take Photos" view is clicked, camera is launched using the intent ACTION_IMAGE_CAPTURE. 
<pre><code>
private class OnCameraClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			if (AppUtil.hasImageCaptureBug() == false) {
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(AppUtil
						.getTempFile(SlideChuteActivity.this)));
			} else {
				intent.putExtra(
						android.provider.MediaStore.EXTRA_OUTPUT,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			}
			startActivityForResult(intent, Constants.CAMERA_PIC_REQUEST);
		}
	}
</code></pre>
When "All photos" view is clicked, PhotoStreamActivity is called which shows grid of device photos.
PhotoStreamActivityIntentWrapper is initialized, which represents a class that wraps the parameters needed for the intent.

private final class OnPhotoStreamListener implements OnClickListener {
<pre><code>
		@Override
		public void onClick(View v) {
			PhotoStreamActivityIntentWrapper streamWrapper = new PhotoStreamActivityIntentWrapper(
					LoginActivity.this);
			streamWrapper
					.startActivityForResult(
							LoginActivity.this,
							PhotoStreamActivityIntentWrapper.ACTIVITY_FOR_RESULT_STREAM_KEY);
		}

	}
</code></pre>	
When "Flickr", "Picasa", "Facebook" or "Instagram" is clicked, GCAccount launches authentication activity 
which starts WebView for sign in. The account type is saved in PreferenceUtil and after
successful authentication, AlbumsActivity gets started.
<pre><code>
private final class OnLoginClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			accountType = (AccountType) v.getTag();
			if (PreferenceUtil.get().hasAccountId(accountType)) {
				accountClicked(PreferenceUtil.get().getAccountId(accountType));
			} else {
				GCAccount.getInstance(getApplicationContext())
						.startAuthenticationActivity(LoginActivity.this,
								accountType, Constants.PERMISSIONS_SCOPE,
								Constants.CALLBACK_URL, Constants.CLIENT_ID,
								Constants.CLIENT_SECRET);
			}
		}
	}	
</code></pre>
In order to launch AlbumsActivity, accountID needs to be placed in the intent.
When starting the authentication activity, GCAccount executes startActivityForResult. 
The accoundID is retrieved from the OnSuccess() method of the api call which is made in OnActivityResult.
<pre><code>
@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			GCAccounts.all(getApplicationContext(), new AccountListParser(),
					new AccountsCallback()).executeAsync();
					}
</code></pre>					

## Albums Activity
This activity shows a list of albums. In order to load the album collection, GCAccounts.objects api
call is made, which returns GCAccountObjectCollection in its callback OnSuccess() method. 
<pre><code>
GCAccounts.objects(getApplicationContext(), wrapper.getAccountId(), new AlbumListParser(), new ObjectsCallback()).executeAsync();
}
private final class ObjectsCallback implements GCHttpCallback<GCAccountObjectCollection> {

		@Override
		public void onSuccess(GCAccountObjectCollection responseData) {
			adapter = new AlbumsAdapter(AlbumsActivity.this, responseData);
			albums.setAdapter(adapter);
			albums.setOnItemClickListener(new OnAlbumsClickListener());
			
		}

		@Override
		public void onHttpException(GCHttpRequestParameters params,
				Throwable exception) {
		}

		@Override
		public void onHttpError(int responseCode, String statusMessage) {
		}

		@Override
		public void onParserException(int responseCode, Throwable exception) {
		}
		
	} 
</code></pre>	
When album item gets clicked, PhotosActivity is called. In order to launch PhotosActivity, 
albumID needs to be placed in the intent. The albumID is retrieved from the AlbumsAdapter.
<pre><code>
private final class OnAlbumsClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			final String albumId = adapter.getItem(position).getId();
			PhotoActivityIntentWrapper photosWrapper = new PhotoActivityIntentWrapper(AlbumsActivity.this);
			photosWrapper.setAlbumId(albumId);
			photosWrapper.setAccountId(wrapper.getAccountId());
			photosWrapper.startActivityForResult(AlbumsActivity.this, PhotoActivityIntentWrapper.ACTIVITY_FOR_RESULT_PHOTO_KEY);
		}
		
	}
</code></pre>

## Photos Activity
PhotosActivity displays GridView of photos the selected album contains. The GridView is loaded
with collection of photos which is retrieved from the api call GCAccounts.objectMedia.
<pre><code>
GCAccounts.objectMedia(getApplicationContext(), wrapper.getAccountId(),
				wrapper.getAlbumId(), new PhotoListParser(),
				new PhotoListCallback()).executeAsync();
	}

	private final class PhotoListCallback implements
			GCHttpCallback<PhotoCollection> {

		@Override
		public void onSuccess(PhotoCollection responseData) {
			adapter = new PhotosAdapter(PhotosActivity.this, responseData);
			grid.setAdapter(adapter);
			grid.setOnScrollListener(adapter);
			grid.setOnItemClickListener(new OnGridItemClickListener());
		}

		@Override
		public void onHttpException(GCHttpRequestParameters params,
				Throwable exception) {

		}

		@Override
		public void onHttpError(int responseCode, String statusMessage) {

		}

		@Override
		public void onParserException(int responseCode, Throwable exception) {

		}

	}
</code></pre>
When GridView item gets clicked, the selected image is returned as GAccountMediaModel in
OnActivityForResult in SlideChuteActivity.
<pre><code>
private final class OnGridItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(final AdapterView<?> parent, final View view,
				final int position, final long id) {
			PhotoActivityIntentWrapper.deliverDataToInitialActivity(
					PhotosActivity.this, adapter.getItem(position), albumId,
					accountId);
			setResult(Activity.RESULT_OK);
			finish();
		}
	}
</code></pre> 

## PhotoSelectCursorAdapter
This adapter class loads the GridView with photos that can be found on the device. 
PhotoSelectCursorAdapter is initialized in PhotoStreamActvity. It searches device photos
using Cursor object.
<pre><code>
public static Cursor getMediaPhotos(Context context) {
		String[] projection = new String[] { MediaStore.Images.Media._ID,
				MediaStore.Images.Media.DATA };
		Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		return context.getContentResolver().query(images, projection, null,
				null, null);
	}
</code></pre> 

## Photos Adapter
PhotosAdapter loads the GridView with GCAccountMediaCollection. 

## Albums Adapter
AlbumsAdapter loads the GridView with GCAccountObjectCollection.

   