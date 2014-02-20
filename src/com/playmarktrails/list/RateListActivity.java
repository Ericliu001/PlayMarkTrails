package com.playmarktrails.list;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.playmarktrails.R;

public class RateListActivity extends ListActivity {
	private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
			"amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
			"ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
			"erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
			"augue", "purus" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArrayList<RowModel> list = new ArrayList<RateListActivity.RowModel>();
		for (String s : items) {
			list.add(new RowModel(s));
		}

		setListAdapter(new RatingAdapter(list));
	}

	static class RowModel {
		String label;
		float rating = 0.0f;

		public RowModel(String label) {
			this.label = label;
		}
		
		@Override
		public String toString() {
			
			return label;
		}
	}

	private RowModel getModel(int position) {
		return (RowModel)(getListAdapter().getItem(position));
	}

	class RatingAdapter extends ArrayAdapter<RowModel> {

		public RatingAdapter(List<RowModel> list) {

			super(RateListActivity.this, R.layout.rating_list_row, R.id.label,
					list);

		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View row =  super.getView(position, convertView, parent);
			RatingBar bar = (RatingBar) row.getTag();
			if (bar == null) {
				bar = (RatingBar) row.findViewById(R.id.rate);
				row.setTag(bar);
				
				bar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
					
					@Override
					public void onRatingChanged(RatingBar ratingBar, float rating,
							boolean fromUser) {
						int mPosition = (Integer) ratingBar.getTag();
						RowModel model = getItem(mPosition);
						model.rating = rating;
						
						
					}
				});
			}
			
			
			bar.setTag(Integer.valueOf(position));
			bar.setRating(getModel(position).rating);
			
			return row;
		}
		
		
		

	}

}
