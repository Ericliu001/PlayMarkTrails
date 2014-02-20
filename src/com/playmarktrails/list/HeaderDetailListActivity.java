package com.playmarktrails.list;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.playmarktrails.R;

public class HeaderDetailListActivity extends ListActivity {
	private static final String[][] items = {
			{ "lorem", "ipsum", "dolor", "sit", "amet" },
			{ "consectetuer", "adipiscing", "elit", "morbi", "vel" },
			{ "ligula", "vitae", "arcu", "aliquet", "mollis" },
			{ "etiam", "vel", "erat", "placerat", "ante" },
			{ "porttitor", "sodales", "pellentesque", "augue", "purus" } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new IconicAdapter());

	}

	class IconicAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			int count = 0;
			for (String[] batch : items) {
				count += batch.length + 1;
			}
			return count;
		}

		@Override
		public Object getItem(int position) {
			int offset = position;
			int batchIndex = 0;

			for (String[] batch : items) {
				if (offset == 0) {
					return Integer.valueOf(batchIndex);
				}

				offset--;

				if (offset < batch.length) {
					return batch[offset];
				}

				offset -= batch.length;
				batchIndex++;
			}

			throw new IllegalArgumentException("Invalid position: "
					+ String.valueOf(position));
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public int getViewTypeCount() {

			return 2;
		}

		@Override
		public int getItemViewType(int position) {
			if (getItem(position) instanceof Integer) {
				return 0;
			}

			return 1;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (getItemViewType(position) == 0) {
				return getHeaderView(position, convertView, parent);
			}
			View row = convertView;
			if (row == null) {
				row = getLayoutInflater().inflate(R.layout.list_row, parent, false);
			}
			ViewHolder holder = (ViewHolder) row.getTag();
			
			if (holder == null) {
				holder = new ViewHolder();
				holder.icon = (ImageView) row.findViewById(R.id.icon);
				holder.lable = (TextView) row.findViewById(R.id.lable);
				holder.size = (TextView) row.findViewById(R.id.size);
			}
			
			String word = (String) getItem(position);
			if (word.length() > 5) {
				holder.icon.setImageResource(R.drawable.delete);
			}else {
				holder.icon.setImageResource(R.drawable.ok);
			}
			
			holder.lable.setText(word);
			holder.size.setText("length: " +  word.length());
			
			return row;
		}

		private View getHeaderView(int position, View convertView,
				ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				row = getLayoutInflater().inflate(R.layout.list_header, parent,
						false);
			}
			TextView lable = (TextView) row.getTag();
			if (lable == null) {

				lable = (TextView) row.findViewById(R.id.label);
				row.setTag(lable);
			}

			Integer batchIndex = (Integer) getItem(position);
			lable.setText("The batch index is: " + batchIndex);

			return row;
		}

	}

	static class ViewHolder {

		ImageView icon = null;
		TextView lable = null;
		TextView size = null;

	}
}
