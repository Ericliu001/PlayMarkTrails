package com.playmarktrails.actionbar;

import com.playmarktrails.R;
import com.playmarktrails.R.layout;
import com.playmarktrails.R.menu;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.view.Menu;

public class TabDemoActivity extends Activity implements TabListener {
	
	private static final String KEY_MODELS = "model";
	private static final String KEY_POSITION = "position";
	private CharSequence[] models = new CharSequence[10];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		for (int i = 0; i < 10; i++) {
			bar.addTab(bar.newTab().setText("Tab #" + String.valueOf(i + 1)).setTabListener(this).setTag(i));
			
		}
		
		if (savedInstanceState != null) {
			models = savedInstanceState.getCharSequenceArray(KEY_MODELS);
			bar.setSelectedNavigationItem(savedInstanceState.getInt(KEY_POSITION));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_demo, menu);
		return true;
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putCharSequenceArray(KEY_MODELS, models);
		outState.putInt(KEY_POSITION, getActionBar().getSelectedNavigationIndex());
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		int i = ((Integer) tab.getTag()).intValue();
		ft.replace(android.R.id.content, TabEditorFragment.newInstance(i, models[i]));
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		int i = ((Integer) tab.getTag()).intValue();
		TabEditorFragment frag = (TabEditorFragment) getFragmentManager().findFragmentById(android.R.id.content);
		if (frag != null) {
			models[i] = frag.getText();
		}
		
	}

}
