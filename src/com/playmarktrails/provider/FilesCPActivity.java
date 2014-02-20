package com.playmarktrails.provider;

import com.playmarktrails.R;
import com.playmarktrails.R.layout;
import com.playmarktrails.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class FilesCPActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		startActivity(new Intent(Intent.ACTION_VIEW,
				Uri.parse(FileProvider.CONTENT_URI + "test.pdf")));
		finish();
	}

}
