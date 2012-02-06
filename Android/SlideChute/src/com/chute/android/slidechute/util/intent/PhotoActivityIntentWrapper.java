package com.chute.android.slidechute.util.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.chute.android.slidechute.app.ChooseServiceActivity;
import com.chute.android.slidechute.app.PhotosActivity;
import com.chute.sdk.collections.GCAccountMediaCollection;
import com.chute.sdk.model.GCAccountMediaModel;

public class PhotoActivityIntentWrapper extends IntentWrapper {

    @SuppressWarnings("unused")
    private static final String TAG = PhotoActivityIntentWrapper.class.getSimpleName();

    public static final int ACTIVITY_FOR_RESULT_PHOTO_KEY = 115;
    private static final String KEY_ACCOUNT_ID = "accountId";
    private static final String KEY_ALBUM_ID = "albumId";
    private static final String KEY_PHOTO_COLLECTION = "photoCollection";
    private static final String KEY_PHOTO_MODEL = "photoModel";

    public PhotoActivityIntentWrapper(Context context) {
        super(context, PhotosActivity.class);
    }

    public PhotoActivityIntentWrapper(Intent intent) {
        super(intent);
    }

    public String getAccountId() {
        return getIntent().getExtras().getString(KEY_ACCOUNT_ID);
    }

    public void setAccountId(String accountId) {
        getIntent().putExtra(KEY_ACCOUNT_ID, accountId);
    }

    public String getAlbumId() {
        return getIntent().getExtras().getString(KEY_ALBUM_ID);
    }

    public void setAlbumId(String albumId) {
        getIntent().putExtra(KEY_ALBUM_ID, albumId);
    }

    public GCAccountMediaCollection getMediaCollection() {
        return getIntent().getExtras().getParcelable(KEY_PHOTO_COLLECTION);
    }

    public void setMediaCollection(GCAccountMediaCollection mediaCollection) {
        getIntent().putExtra(KEY_PHOTO_COLLECTION, (Parcelable) mediaCollection);
    }
    
    public GCAccountMediaModel getMediaModel() {
    	return getIntent().getExtras().getParcelable(KEY_PHOTO_MODEL);
    }
    
    public void setMediaModel(GCAccountMediaModel model) {
    	getIntent().putExtra(KEY_PHOTO_MODEL, (Parcelable)model); 
    }

    public void startActivityForResult(Activity context, int code) {
        context.startActivityForResult(getIntent(), code);
    }

    public static void deliverDataToInitialActivity(final Activity context,
            final GCAccountMediaCollection collection,
            final String albumId,
            final String accountId) {
        final PhotoActivityIntentWrapper wrapper = new PhotoActivityIntentWrapper(new Intent(context,
                ChooseServiceActivity.class));
        wrapper.setAccountId(accountId);
        wrapper.setAlbumId(albumId);
        wrapper.setMediaCollection(collection);
        wrapper.getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        wrapper.startActivity(context);
    }
    
    public static void deliverDataToInitialActivity(final Activity context,
            final GCAccountMediaModel model,
            final String albumId,
            final String accountId) {
        final PhotoActivityIntentWrapper wrapper = new PhotoActivityIntentWrapper(new Intent(context,
                ChooseServiceActivity.class));
        wrapper.setAccountId(accountId);
        wrapper.setAlbumId(albumId);
        wrapper.setMediaModel(model);
        wrapper.getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        wrapper.startActivity(context);
    }
}
