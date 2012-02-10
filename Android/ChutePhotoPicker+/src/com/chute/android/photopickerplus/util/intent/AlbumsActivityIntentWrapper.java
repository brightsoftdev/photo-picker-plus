package com.chute.android.photopickerplus.util.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.UserDictionary;

import com.chute.android.photopickerplus.app.AlbumsActivity;

public class AlbumsActivityIntentWrapper {

	@SuppressWarnings("unused")
	private static final String TAG = AlbumsActivityIntentWrapper.class
			.getSimpleName();

	private static final String KEY_ACCOUNT_ID = "accountId";

	private final Intent intent;

	public AlbumsActivityIntentWrapper(Intent intent) {
		super();
		this.intent = intent;
	}

	public AlbumsActivityIntentWrapper(Context packageContext, Class<?> cls) {
		super();
		intent = new Intent(packageContext, cls);
	}

	public AlbumsActivityIntentWrapper(Context packageContext) {
		super();
		intent = new Intent(packageContext, AlbumsActivity.class);
	}

	public Intent getIntent() {
		return intent;
	}

	public String getAccountId() {
		return intent.getExtras().getString(KEY_ACCOUNT_ID);
	}

	public void setAccountId(String accountId) {
		intent.putExtra(KEY_ACCOUNT_ID, accountId);
	}
	
	public void startActivity(Activity context) {
		context.startActivity(intent);
	}

}
