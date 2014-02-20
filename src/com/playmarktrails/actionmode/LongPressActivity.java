package com.playmarktrails.actionmode;

import java.util.ArrayList;

import com.playmarktrails.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class LongPressActivity extends ListActivity implements
		MultiChoiceModeListener, OnItemLongClickListener {
	private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
			"amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
			"ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
			"erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
			"augue", "purus" };
	private static final String STATE_CHOICE_MODE = "choicemode";
	private static final String STATE_MODEL = "statemodel";
	private ArrayList<String> words;
	
	private ActionMode activeMode = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			initAdapter(null);
		} else {
			initAdapter(savedInstanceState.getStringArrayList(STATE_MODEL));
		}

		getListView().setOnItemLongClickListener(this);
		getListView().setMultiChoiceModeListener(this);

		int choiceMode = (savedInstanceState == null ? ListView.CHOICE_MODE_NONE
				: savedInstanceState.getInt(STATE_CHOICE_MODE));
		getListView().setChoiceMode(choiceMode);
	}

	private void initAdapter(ArrayList<String> startingPoint) {
		
		
		if (startingPoint == null) {
			words = new ArrayList<String>();
			for (String s : items) {
				words.add(s);
			}
		}else {
			words = startingPoint;
		}
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_activated_1, words));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		l.setItemChecked(position, true);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
			int position, long id) {
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		getListView().setItemChecked(position, true);
		startActionMode(this);
		return true;
	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

		return false;
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.action_mode, menu);
		
		activeMode = mode;
		
		mode.setTitle("Awesome ActionMode");
		updateSubtitle(mode);
		return true;
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		if (activeMode != null) {
			activeMode = null;
			getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);
			getListView().setAdapter(getListView().getAdapter());
		}
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

		return false;
	}

	@Override
	public void onItemCheckedStateChanged(ActionMode mode, int position,
			long id, boolean checked) {
		updateSubtitle(mode);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		outState.putInt(STATE_CHOICE_MODE, getListView().getChoiceMode());
		outState.putStringArrayList(STATE_MODEL, words);
	}
	
	private void updateSubtitle(ActionMode mode){
		mode.setSubtitle("(" + getListView().getCheckedItemCount() + ")");
	}

}
