package com.proj.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.proj.food.Food;
import android.util.Log;

public class FoodAddService {

	private static String LOG_TAG = "FoodAddService";
	private final static String Request_MSG = "http://www.slimkicker.com/addFoodEntrie.json";

	
	public String AddFood(String userName, String password, Food food, String servingNo, String servingType)
	{
		HttpClient httpclient = new DefaultHttpClient();

		HttpPost post = new HttpPost(Request_MSG);
		
		// Prepare a request object
		 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("username", userName));
	        nameValuePairs.add(new BasicNameValuePair("password", password));
	        nameValuePairs.add(new BasicNameValuePair("active_date", "20120408"));
	        nameValuePairs.add(new BasicNameValuePair("id", String.valueOf(food.getId())));
	        nameValuePairs.add(new BasicNameValuePair("meal_type", "0"));
	        nameValuePairs.add(new BasicNameValuePair("num_servings", servingNo));
	        nameValuePairs.add(new BasicNameValuePair("serving_type", servingType));
	        
	        try {
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			} catch (UnsupportedEncodingException e1) {
				return ServiceMeta.CONNECTION_FAILURE;
			}
			
		// Execute the request
			HttpResponse response;
			try {
				Log.i(LOG_TAG, "attempt connection");
				response = httpclient.execute(post);
				Log.i(LOG_TAG, "connection made");

				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					// If the response does not enclose an entity, there is no
					// need
					// to worry about connection release

					if (entity != null) {
						// A Simple JSON Response Read
						InputStream instream = entity.getContent();
						Log.i(LOG_TAG, "got stream from server");
						String serverResponse = convertStreamToString(instream);
						return serverResponse;
					}
				} else {
					return ServiceMeta.CONNECTION_FAILURE;
				}
			} catch (Exception e) {
				return ServiceMeta.NO_INFO_FOUND;
			}
		
		return null;
	}
	
	private String convertStreamToString(InputStream is) throws IOException {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the
		 * BufferedReader return null which means there's no more data to
		 * read. Each line will appended to a StringBuilder and returned as
		 * String.
		 */
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			throw new IOException();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new IOException();
			}
		}
		return sb.toString();
	}
	
	
}
