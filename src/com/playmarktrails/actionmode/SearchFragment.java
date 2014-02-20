package com.playmarktrails.actionmode;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;

import com.playmarktrails.R;

public class SearchFragment extends ListFragment implements
		OnQueryTextListener, OnCloseListener {

	private static final String STATE_QUERY = "query";
	private static final String STATE_MODEL = "model";
	private SearchView sv = null;
	CharSequence initialQuery = null;
	private ArrayList<String> words;
	private ArrayAdapter<String> adapter;

	private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
			"amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
			"ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
			"erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
			"augue", "purus" };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		if (savedInstanceState == null) {
			initAdapter(null);
		}else {
			initAdapter(savedInstanceState.getStringArrayList(STATE_MODEL));
			initialQuery = savedInstanceState.getCharSequence(STATE_QUERY);
					
		}

		setHasOptionsMenu(true);
	}

	private void initAdapter(ArrayList<String> startingPoint) {
		if (startingPoint == null) {
			words = new ArrayList<String>();
			for (String s : items) {
				words.add(s);
			}
		} else {
			words = startingPoint;
		}

		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, words);

		setListAdapter(adapter);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		inflater.inflate(R.menu.search, menu);

		configureActionItem(menu);
		configureSearchView(menu);
	}

	private void configureSearchView(Menu menu) {
		MenuItem search = menu.findItem(R.id.search);

		sv = (SearchView) search.getActionView();
		sv.setOnQueryTextListener(this);
		sv.setOnCloseListener(this);
		sv.setSubmitButtonEnabled(false);

		if (initialQuery != null) {
			sv.setIconified(false);
			search.expandActionView();
			sv.setQuery(initialQuery, true);
		}
	}

	private void configureActionItem(Menu menu) {
	}

	@Override
	public boolean onClose() {
		adapter.getFilter().filter("");
		
		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		if (TextUtils.isEmpty(newText)) {
			adapter.getFilter().filter("");
		}else{
			adapter.getFilter().filter(newText.toString());
		}

		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {

		return false;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		outState.putCharSequence(STATE_QUERY, sv.getQuery());
		outState.putStringArrayList(STATE_MODEL, words);
	}

}
