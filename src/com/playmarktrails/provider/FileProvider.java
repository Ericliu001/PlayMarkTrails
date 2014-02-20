package com.playmarktrails.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.media.MediaMuxer.OutputFormat;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

public class FileProvider extends ContentProvider {
	public static final Uri CONTENT_URI = Uri
			.parse("content://com.playmarkmurphy.FileProvider/");

	private static final HashMap<String, String> MIME_TYPES = new HashMap<String, String>();

	static {
		MIME_TYPES.put(".pdf", "application/pdf");
	}

	@Override
	public boolean onCreate() {

		return true;
	}

	static private void copy(InputStream in, File dst) throws IOException {
		FileOutputStream out = new FileOutputStream(dst);
		byte[] buf = new byte[1024];
		int len;

		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode)
			throws FileNotFoundException {

		ParcelFileDescriptor[] pipe = null;
		try {
			pipe = ParcelFileDescriptor.createPipe();
			AssetManager assetsMgr = getContext().getAssets();

			new TransferThread(assetsMgr.open(uri.getLastPathSegment()),
					new ParcelFileDescriptor.AutoCloseOutputStream(pipe[1]))// the write side
					.start();

		} catch (IOException e) {
			throw new FileNotFoundException();
		}

		return pipe[0]; // the read side
	}

	@Override
	public String getType(Uri uri) {
		String path = uri.toString();

		for (String extension : MIME_TYPES.keySet()) {
			if (path.endsWith(extension)) {
				return MIME_TYPES.get(extension);
			}
		}

		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		return 0;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		return 0;
	}
	
	static class TransferThread extends Thread {
		InputStream  in;
		OutputStream  out;
		
		public TransferThread(InputStream in, OutputStream out) {
			this.in = in;
			this.out = out;
		}
		
		@Override
		public void run() {
			byte[] buf = new byte[1024];
			int len;
			
			try {
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				
				in.close();
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
