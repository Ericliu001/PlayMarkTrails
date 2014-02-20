package com.playmarktrails.actionmode;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.playmarktrails.R;

public class ActionModeActivity extends ListActivity {
	
	private ArrayList<String> words;
	
	private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
		"amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
		"ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
		"erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
		"augue", "purus" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initAdapter();
		getListView().setLongClickable(true);
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		getListView().setOnItemLongClickListener(new ActionModeHelper(this, getListView()));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_mode, menu);
		return true;
	}

	public boolean performAction(int itemId, int position) {
		ArrayAdapter<String> adapter = (ArrayAdapter<String>) getListAdapter();
		
		switch (itemId) {
		case R.id.cap:
			String word = words.get(position);
			word = word.toUpperCase();
			
			adapter.remove(words.get(position));
			adapter.insert(word, position);
			return true;

		case R.id.remove:
			adapter.remove(words.get(position));
			return true;
		}
		
		return false;
	}
	
	
	private void initAdapter(){
		words = new ArrayList<String>();
		
		for(String s: items){
			words.add(s);
		}
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words));
	}

}
