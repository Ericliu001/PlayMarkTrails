package com.playmarktrails.actionbar;

import com.playmarktrails.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditorFragment extends Fragment {
	
	private EditText editor = null;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View result = inflater.inflate(R.layout.editor, container, false);
		editor = (EditText) result.findViewById(R.id.editor);
		
		return result;
	}
	
	
	CharSequence getText(){
		return editor.getText();
	}
	
	void setText(CharSequence text){
		editor.setText(text);
	}

	
	void setHint(CharSequence hint){
		editor.setHint(hint);
	}
}
