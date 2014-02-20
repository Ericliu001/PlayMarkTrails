package com.playmarktrails.actionbar;

import com.playmarktrails.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TabEditorFragment extends Fragment {

	private static final String KEY_POSITION = "position";
	private static final String KEY_TEXT = "text";
	private EditText editor = null;

	static TabEditorFragment newInstance(int position, CharSequence text) {
		TabEditorFragment frag = new TabEditorFragment();
		Bundle args = new Bundle();
		args.putInt(KEY_POSITION, position);
		args.putCharSequence(KEY_TEXT, text);
		
		frag.setArguments(args);
		
		return frag;
	}
	
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View result = inflater.inflate(R.layout.editor, container, false);
			editor = (EditText) result.findViewById(R.id.editor);
			
			int position = getArguments().getInt(KEY_POSITION);
			editor.setHint( " where " + (position+1));
			editor.setText(getArguments().getCharSequence(KEY_TEXT));
			return result;
		}
		
	CharSequence getText(){
		return editor.getText();
	}
}
