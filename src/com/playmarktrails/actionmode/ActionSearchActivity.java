package com.playmarktrails.actionmode;

import com.playmarktrails.R;
import com.playmarktrails.R.layout;
import com.playmarktrails.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActionSearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, new SearchFragment()).commit();
		}

	}


}
