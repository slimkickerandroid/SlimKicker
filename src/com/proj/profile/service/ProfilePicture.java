package com.proj.profile.service;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.proj.layout.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ProfilePicture implements IProfilePicture {

	HttpURLConnection conn;
	Bitmap bmImg;

	@Override
	public void setImageOnView(String url, ImageView view) {

		URL myFileUrl = null;
		try {
			myFileUrl = new URL(url);
			conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is;
			is = conn.getInputStream();
			bmImg = BitmapFactory.decodeStream(is);
			view.setImageBitmap(bmImg);
		} catch (IOException e) {
			view.setImageResource(R.drawable.sample);
			return;
		}
	}

}
