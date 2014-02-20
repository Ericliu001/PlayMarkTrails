package com.playmarktrails.actionmode;

import com.playmarktrails.R;

import android.app.Fragment;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class ActionModeHelper implements OnItemLongClickListener,
		ActionMode.Callback {

	ActionModeActivity hostActivity;
	ListView modeView;
	ActionMode activeMode;

	public ActionModeHelper(ActionModeActivity hostActivity, ListView modeView) {
		this.hostActivity = hostActivity;
		this.modeView = modeView;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> view, View row, int position,
			long id) {

		if (activeMode == null) {
			activeMode = hostActivity.startActionMode(this);
		}

		return true;
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		MenuInflater inflater = hostActivity.getMenuInflater();
		inflater.inflate(R.menu.action_mode, menu);
		mode.setTitle("Awesome");
		return true; // must be true to inflate a action mode
	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		
		boolean result = hostActivity.performAction(item.getItemId(), modeView.getCheckedItemPosition());
		
		if (item.getItemId() == R.id.remove) {
			activeMode.finish();
		}

		return result;
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		activeMode = null;
		modeView.clearChoices();
		modeView.requestLayout();
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

		return false;
	}

}
