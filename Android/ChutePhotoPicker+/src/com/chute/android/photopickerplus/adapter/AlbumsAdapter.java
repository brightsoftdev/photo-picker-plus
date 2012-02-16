package com.chute.android.photopickerplus.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chute.android.photopickerplus.R;
import com.chute.sdk.collections.GCAccountObjectCollection;
import com.chute.sdk.model.GCAccountObjectModel;
import com.darko.imagedownloader.ImageLoader;

public class AlbumsAdapter extends BaseAdapter {

    public static final String TAG = AlbumsAdapter.class.getSimpleName();

    private static LayoutInflater inflater;
    private final ImageLoader loader;
    private final GCAccountObjectCollection collection;
    private final Activity context;

    public AlbumsAdapter(final Activity context, final GCAccountObjectCollection collection) {
	this.context = context;
	this.collection = collection;
	inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	loader = ImageLoader.getLoader(context);
    }

    @Override
    public int getCount() {
	return collection.size();
    }

    @Override
    public GCAccountObjectModel getItem(final int position) {
	return collection.get(position);
    }

    @Override
    public long getItemId(final int position) {
	return position;
    }

    public class ViewHolder {
	// ImageView cover;
	TextView name;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

	View vi = convertView;
	ViewHolder holder;
	if (convertView == null) {
	    vi = inflater.inflate(R.layout.album_list_item, null);
	    holder = new ViewHolder();
	    holder.name = (TextView) vi.findViewById(R.id.txt_album_name);
	    vi.setTag(holder);
	} else {
	    holder = (ViewHolder) vi.getTag();
	}
	holder.name.setText(getItem(position).getName());
	return vi;

    }

}
