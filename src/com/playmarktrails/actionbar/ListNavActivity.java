package com.playmarktrails.actionbar;

import com.playmarktrails.R;
import com.playmarktrails.R.layout;
import com.playmarktrails.R.menu;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class ListNavActivity extends Activity implements OnNavigationListener {

	private static final String[] labels = { "Editor #1", "Editor #2",
			"Editor #3", "Editor #4", "Editor #5", "Editor #6", "Editor #7",
			"Editor #8", "Editor #9", "Editor #10", "Editor #11", "Editor #12",
			"Editor #13" };

	private static final String KEY_MODELS = "models";

	private static final String KEY_POSITION = "position";

	private EditorFragment frag = null;
	private CharSequence[] models = new CharSequence[10];
	private int lastPosition = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		frag = (EditorFragment) getFragmentManager().findFragmentById(
				android.R.id.content);

		if (frag == null) {
			frag = new EditorFragment();
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, frag).commit();
		}
		
		if (savedInstanceState != null) {
			models = savedInstanceState.getCharSequenceArray(KEY_MODELS);
		}

		ArrayAdapter<String> nav = null;
		ActionBar bar = getActionBar();
		nav = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, labels);
		nav.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		bar.setListNavigationCallbacks(nav, this);
		
		if (savedInstanceState != null) {
			bar.setSelectedNavigationItem(savedInstanceState.getInt(KEY_POSITION));
		}
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		if (lastPosition > -1) {
			models[lastPosition] = frag.getText();
		}
		lastPosition = itemPosition;
		frag.setText(models[itemPosition]);
		frag.setHint(labels[itemPosition]);
		

		return true;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		if (lastPosition > -1) {
			models[lastPosition] = frag.getText();
		}
		outState.putCharSequenceArray(KEY_MODELS, models);
		outState.putInt(KEY_POSITION, getActionBar().getSelectedNavigationIndex());
		
	}

}
