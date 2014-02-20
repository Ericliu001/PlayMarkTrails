package com.playmarktrails.gridlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TrivialFragment extends Fragment {
	private static final String KEY_LAYOUT_ID = "layoutId";
	
	static TrivialFragment newInstance(int layoutId){
		TrivialFragment frag = new TrivialFragment();
		Bundle args = new Bundle();
		args.putInt(KEY_LAYOUT_ID, layoutId);
		
		frag.setArguments(args);
		return frag;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(getArguments().getInt(KEY_LAYOUT_ID, -1), container, false);
	}
}
