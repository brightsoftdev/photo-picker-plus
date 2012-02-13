package com.chute.android.photopickerplus.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.chute.android.photopickerplus.R;
import com.chute.android.photopickerplus.adapter.AlbumsAdapter;
import com.chute.android.photopickerplus.util.AppUtil;
import com.chute.android.photopickerplus.util.NotificationUtil;
import com.chute.android.photopickerplus.util.intent.AlbumsActivityIntentWrapper;
import com.chute.android.photopickerplus.util.intent.PhotoActivityIntentWrapper;
import com.chute.sdk.api.GCHttpCallback;
import com.chute.sdk.api.account.GCAccounts;
import com.chute.sdk.collections.GCAccountObjectCollection;
import com.chute.sdk.model.GCHttpRequestParameters;

public class AlbumsActivity extends Activity {

    @SuppressWarnings("unused")
    private static final String TAG = AlbumsActivity.class.getSimpleName();

    private ListView albums;
    private AlbumsAdapter adapter;
    private AlbumsActivityIntentWrapper wrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.albums_activity);

	albums = (ListView) findViewById(R.id.albumList);
	albums.setEmptyView(findViewById(R.id.empty_view_layout));
	wrapper = new AlbumsActivityIntentWrapper(getIntent());
	
	TextView title = (TextView) findViewById(R.id.title);
	String albumName = AppUtil.asUpperCaseFirstChar(wrapper.getAccountName().concat(" Albums"));
	title.setText(albumName);

	GCAccounts.objects(getApplicationContext(), wrapper.getAccountId(), new ObjectsCallback())
		.executeAsync();

    }

    private final class ObjectsCallback implements GCHttpCallback<GCAccountObjectCollection> {

	@Override
	public void onSuccess(GCAccountObjectCollection responseData) {
	    adapter = new AlbumsAdapter(AlbumsActivity.this, responseData);
	    albums.setAdapter(adapter);
	    albums.setOnItemClickListener(new OnAlbumsClickListener());
	    NotificationUtil.showAlbumsAdapterToast(getApplicationContext(), adapter.getCount());
	}

	@Override
	public void onHttpException(GCHttpRequestParameters params, Throwable exception) {
	    NotificationUtil.makeConnectionProblemToast(getApplicationContext());
	    toggleEmptyViewErrorMessage();
	}

	@Override
	public void onHttpError(int responseCode, String statusMessage) {
	    NotificationUtil.makeServerErrorToast(getApplicationContext());
	    toggleEmptyViewErrorMessage();
	}

	@Override
	public void onParserException(int responseCode, Throwable exception) {
	    NotificationUtil.makeParserErrorToast(getApplicationContext());
	    toggleEmptyViewErrorMessage();
	}

	public void toggleEmptyViewErrorMessage() {
	    findViewById(R.id.empty_view_layout).setVisibility(View.GONE);
	}
    }

    private final class OnAlbumsClickListener implements OnItemClickListener {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    final String albumId = adapter.getItem(position).getId();
	    final PhotoActivityIntentWrapper photosWrapper = new PhotoActivityIntentWrapper(
		    AlbumsActivity.this);
	    photosWrapper.setAlbumId(albumId);
	    photosWrapper.setAccountId(wrapper.getAccountId());
	    photosWrapper.startActivityForResult(AlbumsActivity.this,
		    PhotoActivityIntentWrapper.ACTIVITY_FOR_RESULT_PHOTO_KEY);
	}

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (resultCode == RESULT_OK) {
	    finish();
	}
    }
    
}
