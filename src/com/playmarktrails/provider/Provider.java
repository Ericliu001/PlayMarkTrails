package com.playmarktrails.provider;

import java.sql.SQLException;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

public class Provider extends ContentProvider {

	private static final String TABLE = "constants";
	private static final UriMatcher MATCHER;
	private static final int CONSTANTS = 1;
	private static final int CONSTANT_ID = 2;

	private DatabaseHelper dbHelper = null;

	public static final class Constants implements BaseColumns {
		public static final Uri CONTENT_URI = Uri
				.parse("contents://com.playmarktrails/constants");
		public static final String DEFAULT_SORT_ORDER = "title";
		public static final String TITLE = "title";
		public static final String VALUE = "value";
	}

	static {
		MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		MATCHER.addURI("com.playmarktrails", "constants", CONSTANTS);
		MATCHER.addURI("com.playmarktrails", "constants", CONSTANT_ID);
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DatabaseHelper(getContext());

		return (dbHelper == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(TABLE);

		String orderBy;

		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = Constants.DEFAULT_SORT_ORDER;
		} else {
			orderBy = sortOrder;
		}

		Cursor c = qb.query(dbHelper.getWritableDatabase(), projection,
				selection, selectionArgs, null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);

		return c;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = dbHelper.getWritableDatabase().delete(TABLE, selection,
				selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		if (isCollectionUri(uri)) {
			return "vnd.android.cursor.dir/constant";
		}

		return "vnd.android.cursor.item/constant";
	}

	private boolean isCollectionUri(Uri uri) {
		return MATCHER.match(uri) == CONSTANTS;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values)
			throws android.database.SQLException {
		long rowId = dbHelper.getWritableDatabase().insert(TABLE,
				Constants.TITLE, values);

		if (rowId > 0) {
			Uri resultUri = ContentUris.withAppendedId(uri, rowId);
			return resultUri;
		}

		throw new android.database.SQLException("Fail to insert to " + uri);

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = dbHelper.getWritableDatabase().update(TABLE, values,
				selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
