package com.playmarktrails.dialog;

import com.playmarktrails.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DialogDance extends DialogFragment implements
		DialogInterface.OnClickListener {

	private View form = null;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		form = getActivity().getLayoutInflater().inflate(R.layout.dialog, null);
		AlertDialog.Builder builder = new Builder(getActivity());

		return builder.setTitle("My Dialog").setView(form)
				.setPositiveButton(android.R.string.ok, this)
				.setNegativeButton(android.R.string.cancel, this).create();
	}

	@Override
	public void onClick(DialogInterface dialog, int buttonId) {
		switch (buttonId) {
		case DialogInterface.BUTTON_NEGATIVE:
			Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT)
					.show();
			break;
		case DialogInterface.BUTTON_NEUTRAL:
			Toast.makeText(getActivity(), "Nothing", Toast.LENGTH_SHORT).show();
			break;
		case DialogInterface.BUTTON_POSITIVE:

			EditText name = (EditText) form.findViewById(R.id.title);
			EditText value = (EditText) form.findViewById(R.id.value);

			String msg = String.format("Name: %1$s / Value: %2$s", name
					.getText().toString(), value.getText().toString());

			Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
	}
}
