package com.playmarktrails.dialog;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.playmarktrails.R;

public class ChronoActivity extends Activity {
	TextView tvDateTime;
	Calendar dateTime = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chrono);

		tvDateTime = (TextView) findViewById(R.id.tvDateTime);

		updateLabel();
	}

	private void updateLabel() {
		tvDateTime.setText(DateUtils.formatDateTime(this,
				dateTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_SHOW_TIME));
	}

	public void chooseDate(View v) {
		new DatePickerDialog(this, dateListener, dateTime.get(Calendar.YEAR),
				dateTime.get(Calendar.MONTH),
				dateTime.get(Calendar.DAY_OF_MONTH)).show();
	}

	public void chooseTime(View v) {
		new TimePickerDialog(this, timeListener,
				dateTime.get(Calendar.HOUR_OF_DAY),
				dateTime.get(Calendar.MINUTE), true).show();
	}

	DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dateTime.set(Calendar.YEAR, year);
			dateTime.set(Calendar.MONTH, monthOfYear);
			dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
			
		}
	};
	
	TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener(){

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			dateTime.set(Calendar.MINUTE, minute);
			updateLabel();
		}
		
	};
	
	public void showDialog(View view){
		new DialogDance().show(getFragmentManager(), "Spring");
	}
}
