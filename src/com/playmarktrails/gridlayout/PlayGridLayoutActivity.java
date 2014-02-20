package com.playmarktrails.gridlayout;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.playmarktrails.R;

public class PlayGridLayoutActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_grid_layout);
		
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		
		pager.setAdapter(buildAdapter());
	}

	private PagerAdapter buildAdapter() {
		
		return new SampleAdapter(this, getSupportFragmentManager());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_grid_layout, menu);
		return true;
	}

}
