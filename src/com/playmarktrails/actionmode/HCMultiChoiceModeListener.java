package com.playmarktrails.actionmode;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ListView;

import com.playmarktrails.R;

public class HCMultiChoiceModeListener implements MultiChoiceModeListener {
	ActionModeMCActivity hostActivity;
	ActionMode activeMode;
	ListView lv;
	

	public HCMultiChoiceModeListener(ActionModeMCActivity hostActivity,
			ListView lv) {
		this.hostActivity = hostActivity;
		this.lv = lv;
	}
	
	private void updateSubtitle(ActionMode mode) {
		mode.setSubtitle("(" + lv.getCheckedItemCount() + ")");
	}
	
	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		MenuInflater inflater = hostActivity.getMenuInflater();
		inflater.inflate(R.menu.action_mode, menu);
		mode.setTitle("Awesome Title");
		mode.setSubtitle("(1)");
		activeMode = mode;
		
		return true;
	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		boolean result = hostActivity.performAction(item);
		updateSubtitle(activeMode);
		
		return result;
	}

	

	

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		activeMode = null;
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


}
