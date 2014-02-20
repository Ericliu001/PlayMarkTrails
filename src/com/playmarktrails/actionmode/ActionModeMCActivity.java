package com.playmarktrails.actionmode;

import java.util.ArrayList;
import java.util.Collections;

import com.playmarktrails.R;
import com.playmarktrails.R.id;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActionModeMCActivity extends ListActivity {
	private ArrayList<String> words = null;
	
	private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
		"amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
		"ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
		"erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
		"augue", "purus" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initAdapter();
		
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		getListView().setMultiChoiceModeListener(new HCMultiChoiceModeListener(this, getListView()));
	}

	private void initAdapter() {
		words = new ArrayList<String>();
		for(String s: items){
			words.add(s);
		}
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, words));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		l.setItemChecked(position, true);
	}

	public boolean performAction(MenuItem item){
		ArrayAdapter<String> adapter = (ArrayAdapter<String>) getListAdapter();
		SparseBooleanArray checked = getListView().getCheckedItemPositions();
		
		switch (item.getItemId()) {
		case R.id.cap:
			for (int i = 0; i < checked.size(); i++) {
				if (checked.valueAt(i)) {
					int position = checked.keyAt(i);
					String word = words.get(position);
					
					word = word.toUpperCase();
					adapter.remove(words.get(position));
					adapter.insert(word, position);
				}
			}
			return true;

		case R.id.remove:
			ArrayList<Integer> positions = new ArrayList<Integer>();
			for (int i = 0; i < checked.size(); i++) {
				if (checked.valueAt(i)) {
					positions.add(checked.keyAt(i));
				}
			}
			
			Collections.sort(positions, Collections.reverseOrder());
			for(int position: positions){
				adapter.remove(words.get(position));
			}
			
			getListView().clearChoices();
			
			return true;
		}
		
		return false;
	}

}
