package com.chute.android.slidechute.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.chute.android.slidechute.R;
import com.chute.android.slidechute.adapter.PhotoSelectCursorAdapter;
import com.chute.android.slidechute.dao.MediaDAO;
import com.chute.android.slidechute.util.NotificationUtil;
import com.chute.android.slidechute.util.intent.CameraRollActivityIntentWrapper;
import com.chute.android.slidechute.util.intent.PhotoStreamActivityIntentWrapper;

public class PhotoStreamActivity extends Activity {

	public static final String TAG = PhotoStreamActivity.class.getSimpleName();
	private GridView grid;
	private PhotoSelectCursorAdapter gridAdapter;
	CameraRollActivityIntentWrapper cameraRollWrapper;
	PhotoStreamActivityIntentWrapper photoStreamWrapper;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photos_select);

		grid = (GridView) findViewById(R.id.gridView);
		grid.setEmptyView(findViewById(R.id.empty_view_layout));
		cameraRollWrapper = new CameraRollActivityIntentWrapper(getIntent());
		photoStreamWrapper = new PhotoStreamActivityIntentWrapper(getIntent());
		new LoadCursorTask().execute();

	}

	private class LoadCursorTask extends AsyncTask<Void, Void, Cursor> {

		@Override
		protected Cursor doInBackground(final Void... arg0) {
			if (photoStreamWrapper.getPhotoBoolean()) {
				return MediaDAO.getAllMediaPhotos(getApplicationContext());
			} else
				return MediaDAO.getCameraPhotos(getApplicationContext());
		}

		@Override
		protected void onPostExecute(final Cursor result) {
			super.onPostExecute(result);
			if (result == null) {
				return;
			}
			if (gridAdapter == null) {
				gridAdapter = new PhotoSelectCursorAdapter(
						PhotoStreamActivity.this, result);
				grid.setAdapter(gridAdapter);
				grid.setOnItemClickListener(new OnGridItemClickListener());
			} else {
				gridAdapter.changeCursor(result);
			}
			NotificationUtil.showPhotosAdapterToast(getApplicationContext(),
					gridAdapter.getCount());
		}
	}

	private final class OnGridItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(final AdapterView<?> parent, final View view,
				final int position, final long id) {
			final PhotoStreamActivityIntentWrapper wrapper = new PhotoStreamActivityIntentWrapper(
					new Intent());
			wrapper.setAssetPath(gridAdapter.getItem(position));
			setResult(RESULT_OK, wrapper.getIntent());
			finish();
		}
	}

}
