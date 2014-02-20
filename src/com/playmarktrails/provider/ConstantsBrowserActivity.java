package com.playmarktrails.provider;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

import com.playmarktrails.R;

public class ConstantsBrowserActivity extends ListActivity {

	
	private static final String[] PROJECTION = new String[]{
		Provider.Constants._ID, Provider.Constants.TITLE,
		Provider.Constants.VALUE
	};
	private Cursor constantsCursor;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		constantsCursor = managedQuery(Provider.Constants.CONTENT_URI, PROJECTION, null, null, null);
		
		
		String[] from = new String[]{Provider.Constants.TITLE, Provider.Constants.VALUE};
		int[] to = new  int[]{R.id.provider_title, R.id.provider_value};
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.provider_row, constantsCursor, from, to,0);
		setListAdapter(adapter);
	}

}
