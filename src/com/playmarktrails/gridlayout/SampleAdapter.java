package com.playmarktrails.gridlayout;

import java.util.ArrayList;

import com.playmarktrails.R;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SampleAdapter extends FragmentPagerAdapter {
	
	static ArrayList<Sample> sampleList = new ArrayList<Sample>();
	private Context mContext = null;
	
	static{
		sampleList.add(new Sample( R.layout.some_row, R.string.row));
		sampleList.add(new Sample(R.layout.column, R.string.column));
		sampleList.add(new Sample(R.layout.table, R.string.table));
		sampleList.add(new Sample(R.layout.table_flex, R.string.flexible_table));
		sampleList.add(new Sample(R.layout.implicit, R.string.implicit));
		sampleList.add(new Sample(R.layout.spans, R.string.spans));
	}

	public SampleAdapter(Context context, FragmentManager fm) {
		
		super(fm);
		this.mContext = context;
	}

	@Override
	public Fragment getItem(int position) {

		return TrivialFragment.newInstance(getSample(position).layoutId);
	}

	@Override
	public int getCount() {

		return sampleList.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		
		return mContext.getString(getSample(position).titleId);
	}
	
	private Sample getSample(int position){
		return sampleList.get(position);
	}

}
