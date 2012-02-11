package com.chute.android.photopickerplus.util.intent;

import android.app.Activity;
import android.content.Intent;

import com.chute.android.photopickerplus.app.ChooseServiceActivity;
import com.chute.sdk.collections.GCAccountMediaCollection;
import com.chute.sdk.model.GCAccountMediaModel;

public class IntentUtil {

    public static void deliverDataToInitialActivity(final Activity context,
	    final GCAccountMediaCollection collection, final String albumId, final String accountId) {
	final PhotoActivityIntentWrapper wrapper = new PhotoActivityIntentWrapper(new Intent(
		context, ChooseServiceActivity.class));
	wrapper.setAccountId(accountId);
	wrapper.setAlbumId(albumId);
	wrapper.setMediaCollection(collection);
	wrapper.getIntent().addFlags(
		Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	wrapper.startActivity(context);
    }

    public static void deliverDataToInitialActivity(final Activity context,
	    final GCAccountMediaModel model, final String albumId, final String accountId) {
	final PhotoActivityIntentWrapper wrapper = new PhotoActivityIntentWrapper(new Intent(
		context, ChooseServiceActivity.class));
	wrapper.setAccountId(accountId);
	wrapper.setAlbumId(albumId);
	wrapper.setMediaModel(model);
	wrapper.getIntent().addFlags(
		Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	wrapper.startActivity(context);
    }
}
