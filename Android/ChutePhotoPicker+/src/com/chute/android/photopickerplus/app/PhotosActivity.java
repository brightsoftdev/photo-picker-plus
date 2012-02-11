package com.chute.android.photopickerplus.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.chute.android.photopickerplus.R;
import com.chute.android.photopickerplus.adapter.PhotosAdapter;
import com.chute.android.photopickerplus.util.NotificationUtil;
import com.chute.android.photopickerplus.util.intent.IntentUtil;
import com.chute.android.photopickerplus.util.intent.PhotoActivityIntentWrapper;
import com.chute.sdk.api.GCHttpCallback;
import com.chute.sdk.api.account.GCAccounts;
import com.chute.sdk.collections.GCAccountMediaCollection;
import com.chute.sdk.model.GCHttpRequestParameters;

public class PhotosActivity extends Activity {

    public static final String TAG = PhotosActivity.class.getSimpleName();

    private GridView grid;
    private PhotosAdapter adapter;

    private String accountId;
    private String albumId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.photos_select);

	grid = (GridView) findViewById(R.id.gridView);

	PhotoActivityIntentWrapper wrapper = new PhotoActivityIntentWrapper(getIntent());
	accountId = wrapper.getAccountId();
	albumId = wrapper.getAlbumId();
	grid.setEmptyView(findViewById(R.id.empty_view_layout));
	GCAccounts
		.objectMedia(getApplicationContext(), accountId, albumId, new PhotoListCallback())
		.executeAsync();
    }

    private final class PhotoListCallback implements GCHttpCallback<GCAccountMediaCollection> {

	@Override
	public void onSuccess(GCAccountMediaCollection responseData) {
	    adapter = new PhotosAdapter(PhotosActivity.this, responseData);
	    grid.setAdapter(adapter);
	    grid.setOnItemClickListener(new OnGridItemClickListener());
	    NotificationUtil.showPhotosAdapterToast(getApplicationContext(), adapter.getCount());
	}

	@Override
	public void onHttpException(GCHttpRequestParameters params, Throwable exception) {

	}

	@Override
	public void onHttpError(int responseCode, String statusMessage) {

	}

	@Override
	public void onParserException(int responseCode, Throwable exception) {

	}

    }

    private final class OnGridItemClickListener implements OnItemClickListener {

	@Override
	public void onItemClick(final AdapterView<?> parent, final View view, final int position,
		final long id) {
	    IntentUtil.deliverDataToInitialActivity(PhotosActivity.this, adapter.getItem(position),
		    albumId, accountId);
	    setResult(Activity.RESULT_OK);
	    finish();
	}
    }
}
